package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import android.util.Patterns
import com.androidapp.myportfolioappandroid.core.common.AppError
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.AuthError
import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): AppResult<FirebaseUserModel> {
        if (fullName.isBlank()) {
            return AppResult.Error(AppError.Auth(AuthError.InvalidFullName))
        }
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AppResult.Error(AppError.Auth(AuthError.InvalidEmail))
        }
        if (password.length < 6) {
            return AppResult.Error(AppError.Auth(AuthError.WeakPassword))
        }
        if (password != confirmPassword) {
            return AppResult.Error(AppError.Auth(AuthError.PasswordMismatch))
        }
        return authRepository.register(fullName.trim(), email.trim(), password)
    }
}