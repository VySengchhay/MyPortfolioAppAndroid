package com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}