package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class CancelUnverifiedRegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): AppResult<Unit> = authRepository.deleteUnverifiedAccount()
}
