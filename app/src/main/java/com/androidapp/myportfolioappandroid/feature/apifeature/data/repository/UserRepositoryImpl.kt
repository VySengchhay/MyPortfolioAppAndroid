package com.androidapp.myportfolioappandroid.feature.apifeature.data.repository

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper.toDomain
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.UserApiService
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequest
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import jakarta.inject.Inject
import retrofit2.Response

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun getUserList(): AppResult<List<User>> {
        return try {
            val users = userApiService.getUsers().map { it.toDomain() }

            AppResult.Success(users)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }

    override suspend fun addUser(user: AddUser): AppResult<CreateUserResponse> {
        return try {
            val request = UserApiRequest(
                name = user.name,
                email = user.email
            )

            val response = userApiService.addUser(request)

            AppResult.Success(response)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }
}