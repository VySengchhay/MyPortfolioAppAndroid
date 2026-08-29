package com.androidapp.myportfolioappandroid.feature.apifeature.data.repository

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper.toDomain
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.UserApiService
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UpdateUserRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import jakarta.inject.Inject

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

    override suspend fun addUser(user: AddUser): AppResult<CreateUser> {
        return try {
            val request = UserApiRequestDto(
                name = user.name,
                email = user.email
            )

            val response = userApiService.addUser(request).toDomain()

            AppResult.Success(response)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }

    override suspend fun updateUser(
        id: Int,
        user: User
    ): AppResult<UpdateUser> {
        return try {
            val request = UpdateUserRequestDto(
                id = user.id,
                name = user.name,
                email = user.email
            )

            val response = userApiService.updateUser(
                id,
                request
            ).toDomain()

            AppResult.Success(response)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }
}
