package com.androidapp.myportfolioappandroid.feature.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authStateFlow = _authState.asStateFlow()

    init {
        checkAuthState()
    }

    fun onEvent(
        event: AuthEvent
    ) {
        when (event) {
            is AuthEvent.Login -> {
                login(event.email, event.password)
            }

            is AuthEvent.Signup -> {
                signup(event.email, event.password, event.confirmPassword)
            }

            AuthEvent.SignOut -> {
                signOut()
            }
        }
    }

    private fun checkAuthState() {
        if (auth.currentUser != null) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.UnAuthenticated
        }
    }

    private fun login(
        email: String,
        password: String
    ) {
        _authState.value = AuthState.Loading

        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Please fill in all fields.")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong.")
                }
            }
    }

    private fun signup(
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        _authState.value = AuthState.Loading

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _authState.value = AuthState.Error("Please fill in all fields.")
            return
        }

        if (password != confirmPassword) {
            _authState.value = AuthState.Error("Passwords do not match.")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(
                        task.exception?.message ?: "Something went wrong."
                    )
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }
}
