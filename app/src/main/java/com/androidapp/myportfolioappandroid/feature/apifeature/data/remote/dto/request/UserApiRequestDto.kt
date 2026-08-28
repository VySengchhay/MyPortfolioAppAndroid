package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UserApiRequestDto(
    val name: String,
    val email: String
)
