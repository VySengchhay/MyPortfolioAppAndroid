package com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreatedUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User

fun UserResponseDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}

fun CreateUserResponseDto.toDomain(): CreatedUser {
    return CreatedUser(
        message = message,
        user = user.toDomain()
    )
}
