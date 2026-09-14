package com.androidapp.myportfolioappandroid.feature.auth.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUserFlow: Flow<FirebaseUserModel?>

    suspend fun login(email: String, password: String) : AppResult<FirebaseUserModel>
    suspend fun forgotPassword(email: String) : AppResult<Unit>
    suspend fun register(fullName: String, email: String, password: String) : AppResult<FirebaseUserModel>
    fun logout()

    suspend fun reloadCurrentUser(): AppResult<FirebaseUserModel>
    suspend fun deleteUnverifiedAccount(): AppResult<Unit>
}

