package com.androidapp.myportfolioappandroid.feature.auth.domain.model

data class FirebaseUserModel(
    val uid: String,
    val email: String?,
    val displayName: String?,
    val isEmailVerified: Boolean,
    val createdAtMillis: Long,
)
