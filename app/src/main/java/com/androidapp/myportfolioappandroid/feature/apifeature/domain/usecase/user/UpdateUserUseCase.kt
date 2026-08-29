package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.BaseUseCase
import jakarta.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): AppResult<UpdateUser> {
        return userRepository.updateUser(user.id, user)
    }
}