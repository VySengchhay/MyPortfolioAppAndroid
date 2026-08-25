package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserListResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}