package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponseDto(
    @SerialName("message")
    val message: String,

    @SerialName("data")
    val user: UserResponseDto
)
