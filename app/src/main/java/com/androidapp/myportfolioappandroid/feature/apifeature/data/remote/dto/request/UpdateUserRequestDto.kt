package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequestDto(
    val id: Int,
    val name: String,
    val email: String
)
