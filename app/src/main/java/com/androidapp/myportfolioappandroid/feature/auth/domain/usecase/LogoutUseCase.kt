package com.androidapp.myportfolioappandroid.feature.auth.domain.usecase

import com.androidapp.myportfolioappandroid.core.database.AppDatabase
import com.androidapp.myportfolioappandroid.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
//    private val userPreferencesRepository: UserPreferencesRepositoy, // e.g. clear cached token/DataStore
    private val localDatabase: AppDatabase                            // e.g. clear Room cache on sign-out
) {
    suspend operator fun invoke() {
        authRepository.logout()
//        userPreferencesRepository.clear()
        withContext(Dispatchers.IO) {
            localDatabase.clearAllTables()
        }
    }
}