package com.androidapp.myportfolioappandroid.feature.auth.data.repository

import android.util.Log
import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.data.mapper.toDomain
import com.androidapp.myportfolioappandroid.feature.auth.data.mapper.toDomainOrNull
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.toAppError
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import jakarta.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

private const val TAG = "AuthRepositoryImpl"
private const val CLEANUP_APP_NAME = "unverified-account-cleanup"

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    // AuthStateListener only fires on sign-in/sign-out, not after reload(), so a newly
    // verified email would never reach observers without this extra signal.
    private val userRefreshes = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    // Separate Firebase app instance so checking a leftover account does not sign it in on the
    // main instance, which would show the verification dialog and race with registration.
    private val cleanupAuth: FirebaseAuth by lazy {
        val defaultApp = FirebaseApp.getInstance()
        val context = defaultApp.applicationContext
        val cleanupApp = FirebaseApp.getApps(context).firstOrNull { it.name == CLEANUP_APP_NAME }
            ?: FirebaseApp.initializeApp(context, defaultApp.options, CLEANUP_APP_NAME)
        FirebaseAuth.getInstance(cleanupApp)
    }

    override val currentUserFlow: Flow<FirebaseUserModel?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser.toDomainOrNull())
        }
        firebaseAuth.addAuthStateListener(listener)
        launch {
            userRefreshes.collect {
                trySend(firebaseAuth.currentUser.toDomainOrNull())
            }
        }
        awaitClose {
            firebaseAuth.removeAuthStateListener(listener)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): AppResult<FirebaseUserModel> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user?.toDomain()
                ?: return AppResult.Error(AppError.Unknown())
            AppResult.Success(user)
        } catch (e: Exception) {
            AppResult.Error(e.toAppError())
        }
    }

    override suspend fun forgotPassword(email: String): AppResult<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Error(e.toAppError())
        }
    }

    override suspend fun register(
        fullName: String,
        email: String,
        password: String,
    ): AppResult<FirebaseUserModel> {
        return try {
            val firebaseUser = createUser(email, password)
                ?: return AppResult.Error(AppError.Unknown(IllegalStateException("Null user")))

            val profileUpdates = userProfileChangeRequest {
                displayName = fullName
            }
            firebaseUser.updateProfile(profileUpdates).await()

            runCatching { firebaseUser.sendEmailVerification().await() }

            firebaseUser.reload().await()
            val updatedUser = firebaseAuth.currentUser

            AppResult.Success(updatedUser.toDomainOrNull() ?: firebaseUser.toDomain())
            } catch (e: Exception) {
            AppResult.Error(e.toAppError())
        }
    }

    private suspend fun createUser(email: String, password: String): FirebaseUser? {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
        } catch (e: FirebaseAuthUserCollisionException) {
            if (!deleteLeftoverUnverifiedAccount(email, password)) throw e
            firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
        }
    }

    /**
     * Firebase creates the account before the email is verified. If an earlier attempt left one
     * behind (app killed while waiting, or the delete failed offline), remove it so the email
     * can be registered again. Only possible when the same password is used.
     */
    private suspend fun deleteLeftoverUnverifiedAccount(email: String, password: String): Boolean {
        return try {
            val existing = cleanupAuth.signInWithEmailAndPassword(email, password).await().user
                ?: return false
            if (existing.isEmailVerified) return false
            existing.delete().await()
            Log.d(TAG, "deleteLeftoverUnverifiedAccount: removed leftover account for $email")
            true
        } catch (e: Exception) {
            Log.w(TAG, "deleteLeftoverUnverifiedAccount: could not remove account for $email", e)
            false
        } finally {
            cleanupAuth.signOut()
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun reloadCurrentUser(): AppResult<FirebaseUserModel> {
        return try {
            val user = firebaseAuth.currentUser
                ?: return AppResult.Error(AppError.Unauthorized)
            user.reload().await()
            val refreshed = firebaseAuth.currentUser?.toDomain()
                ?: return AppResult.Error(AppError.Unauthorized)
            userRefreshes.tryEmit(Unit)
            AppResult.Success(refreshed)
        } catch (e: Exception) {
            AppResult.Error(e.toAppError())
        }
    }

    override suspend fun deleteUnverifiedAccount(): AppResult<Unit> {
        val user = firebaseAuth.currentUser
        if (user == null || user.isEmailVerified) {
            Log.w(TAG, "deleteUnverifiedAccount: nothing to delete (user=${user?.email}, verified=${user?.isEmailVerified})")
            return AppResult.Error(AppError.Unauthorized)
        }
        Log.d(TAG, "deleteUnverifiedAccount: attempting delete for ${user.email}")
        return try {
            user.delete().await()
            Log.d(TAG, "deleteUnverifiedAccount: delete succeeded for ${user.email}")
            AppResult.Success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "deleteUnverifiedAccount: delete FAILED for ${user.email}", e)
            AppResult.Error(e.toAppError())
        } finally {
            // Guarantee the local session is cleared even if the remote delete failed,
            // so an abandoned/unverified account can never leave the user stuck signed in.
            firebaseAuth.signOut()
        }
    }

}
