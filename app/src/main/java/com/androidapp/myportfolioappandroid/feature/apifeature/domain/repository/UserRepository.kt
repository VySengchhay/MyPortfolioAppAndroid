package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserListResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {
    suspend fun getUserList(): AppResult<List<User>>

    suspend fun addUser(user: AddUser): AppResult<CreateUserResponse>
}