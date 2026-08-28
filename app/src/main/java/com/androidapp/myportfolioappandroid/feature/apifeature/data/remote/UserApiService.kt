package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserResponseDto>

    @POST("users")
    suspend fun addUser(
        @Body user: UserApiRequestDto
    ): CreateUserResponseDto
}