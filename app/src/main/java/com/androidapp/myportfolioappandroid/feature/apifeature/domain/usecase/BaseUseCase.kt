package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase

abstract class BaseUseCase<in P, out R> {
    suspend operator fun invoke(params: P): R {
        return execute(params)
    }
    protected abstract suspend fun execute(params: P): R
}