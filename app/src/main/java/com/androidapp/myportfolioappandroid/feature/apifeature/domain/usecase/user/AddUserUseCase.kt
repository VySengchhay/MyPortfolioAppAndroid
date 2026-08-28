package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreatedUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.BaseUseCase
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<AddUser, AppResult<CreatedUser>>() {
    override suspend fun execute(params: AddUser): AppResult<CreatedUser> {
        return userRepository.addUser(params)
    }
}
