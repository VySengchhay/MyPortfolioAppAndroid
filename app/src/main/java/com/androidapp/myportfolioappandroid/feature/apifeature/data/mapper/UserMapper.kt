package com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UpdateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User

fun UserResponseDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}

fun CreateUserResponseDto.toDomain(): CreateUser {
    return CreateUser(
        message = message,
        user = user.toDomain()
    )
}

fun UpdateUserResponseDto.toDomain(): UpdateUser {
    return UpdateUser(
        message = message,
        user = user.toDomain()
    )
}
