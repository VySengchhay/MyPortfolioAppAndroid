package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User

interface UserRepository {
    suspend fun getUserList(
        name: String? = null
    ): AppResult<List<User>>

    suspend fun addUser(user: AddUser): AppResult<CreateUser>

    suspend fun updateUser(
        id: Int,
        user: User
    ): AppResult<UpdateUser>

    suspend fun deleteUser(
        id: Int
    ): AppResult<DeleteUser>
}
