package com.androidapp.myportfolioappandroid.feature.apifeature.data.repository

import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.mapper.toDomain
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.UserApiService
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UpdateUserRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun getUserList(
        name: String?
    ): AppResult<List<User>> {
        return try {
            val users = userApiService.getUsers(name).map { it.toDomain() }

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

    override suspend fun deleteUser(
        id: Int
    ): AppResult<DeleteUser> {
        return try {
            val response = userApiService.deleteUser(id).toDomain()
            AppResult.Success(response)
        } catch (e: Exception) {
            AppResult.Error(AppError.Unknown(e))
        }
    }
}
