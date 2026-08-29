package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int): AppResult<DeleteUser> {
        return userRepository.deleteUser(id)
    }
}