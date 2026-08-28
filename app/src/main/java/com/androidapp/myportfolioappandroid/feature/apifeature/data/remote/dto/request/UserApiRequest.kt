package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class UserApiRequest(
    val name: String,
    val email: String
)
