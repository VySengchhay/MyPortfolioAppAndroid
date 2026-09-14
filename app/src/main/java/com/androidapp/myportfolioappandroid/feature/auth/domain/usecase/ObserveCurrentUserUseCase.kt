package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import com.androidapp.myportfolioappandroid.feature.auth.domain.model.FirebaseUserModel
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<FirebaseUserModel?> = repository.currentUserFlow
}