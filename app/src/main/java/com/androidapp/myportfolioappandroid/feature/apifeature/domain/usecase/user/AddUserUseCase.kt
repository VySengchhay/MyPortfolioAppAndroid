package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.BaseUseCase
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<AddUser, AppResult<CreateUser>>() {
    override suspend fun execute(params: AddUser): AppResult<CreateUser> {
        return userRepository.addUser(params)
    }
}
