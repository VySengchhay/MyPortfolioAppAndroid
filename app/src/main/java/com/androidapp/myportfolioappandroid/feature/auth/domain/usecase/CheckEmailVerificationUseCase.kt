package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class CheckEmailVerificationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): AppResult<FirebaseUserModel> = authRepository.reloadCurrentUser()
}
