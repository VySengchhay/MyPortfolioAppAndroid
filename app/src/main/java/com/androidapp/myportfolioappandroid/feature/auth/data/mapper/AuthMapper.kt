package com.androidapp.myportfolioappandroid.feature.auth.data.mapper

import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): FirebaseUserModel {
    return FirebaseUserModel(
        uid = uid,
        email = email,
        displayName = displayName,
        isEmailVerified = isEmailVerified,
        createdAtMillis = metadata?.creationTimestamp ?: 0L
    )
}

fun FirebaseUser?.toDomainOrNull(): FirebaseUserModel? {
    return this?.toDomain()
}