package com.androidapp.myportfolioappandroid.feature.auth.domain.model

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

sealed class AuthError(val message: String) {
    data object InvalidEmail : AuthError("Please enter a valid email address")
    data object InvalidFullName : AuthError("Please enter your full name")
    data object WeakPassword : AuthError("Password should be at least 6 characters")
    data object PasswordMismatch : AuthError("Passwords do not match")
    data object WrongPassword : AuthError("Incorrect email or password")
    data object UserNotFound : AuthError("Incorrect email or password")
    data object UserCollision : AuthError("An account already exists with this email")
}

fun Throwable.toAppError(): AppError = when (this) {
    is FirebaseAuthInvalidCredentialsException -> AppError.Auth(AuthError.WrongPassword)
    is FirebaseAuthInvalidUserException -> AppError.Auth(AuthError.UserNotFound)
    is FirebaseAuthUserCollisionException -> AppError.Auth(AuthError.UserCollision)
    is FirebaseNetworkException -> AppError.Network
    else -> AppError.Unknown(this)
}