package com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User

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
