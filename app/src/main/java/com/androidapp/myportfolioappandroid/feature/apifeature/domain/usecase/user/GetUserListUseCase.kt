package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        name: String?
    ): AppResult<List<User>> {
        return userRepository.getUserList(
            name = name
        )
    }
}
