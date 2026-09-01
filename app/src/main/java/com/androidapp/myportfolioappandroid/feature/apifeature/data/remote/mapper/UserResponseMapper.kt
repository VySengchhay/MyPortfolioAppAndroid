package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.mapper

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.user.UserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.user.CreateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.user.DeleteUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.user.UpdateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User

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

fun DeleteUserResponseDto.toDomain(): DeleteUser {
    return DeleteUser(
        message = message,
        user = user.toDomain()
    )
}
