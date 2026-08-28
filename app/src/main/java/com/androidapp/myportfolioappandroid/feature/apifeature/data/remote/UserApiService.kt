package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequest
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @POST("users")
    suspend fun addUser(
        @Body user: UserApiRequest
    ): CreateUserResponse
}