package com.androidapp.myportfolioappandroid.feature.apifeature.data.remote

import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UpdateUserRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.DeleteUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UpdateUserResponseDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("name") name: String? = null,
    ): List<UserResponseDto>

    @POST("users")
    suspend fun addUser(
        @Body user: UserApiRequestDto
    ): CreateUserResponseDto

    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UpdateUserRequestDto
    ): UpdateUserResponseDto

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): DeleteUserResponseDto
}