package com.androidapp.myportfolioappandroid.feature.auth

sealed interface AuthEvent {
    data class Login(
        val email: String,
        val password: String
    ) : AuthEvent

    data class Signup(
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : AuthEvent

    data object SignOut : AuthEvent
}
