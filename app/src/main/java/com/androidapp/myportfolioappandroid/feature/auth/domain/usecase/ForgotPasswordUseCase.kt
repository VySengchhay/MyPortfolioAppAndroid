package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import android.util.Patterns
import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.AuthError
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email: String): AppResult<Unit> {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AppResult.Error(AppError.Auth(AuthError.InvalidEmail))
        }

        return authRepository.forgotPassword(email)
    }
}