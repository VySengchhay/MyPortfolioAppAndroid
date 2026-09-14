package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import android.util.Patterns
import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.AuthError
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AppResult<FirebaseUserModel> {
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AppResult.Error(AppError.Auth(AuthError.InvalidEmail))
        }
        if (password.length < 6) {
            return AppResult.Error(AppError.Auth(AuthError.WeakPassword))
        }
        return authRepository.login(email.trim(), password)
    }
}