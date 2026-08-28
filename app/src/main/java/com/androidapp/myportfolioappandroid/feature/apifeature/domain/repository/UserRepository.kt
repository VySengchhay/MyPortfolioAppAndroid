package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequestDto
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreatedUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User

interface UserRepository {
    suspend fun getUserList(): AppResult<List<User>>

    suspend fun addUser(user: AddUser): AppResult<CreatedUser>
}
