package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase

abstract class BaseSyncUseCase<in P, out R> {
    operator fun invoke(params: P): Result<R> {
        return execute(params)
    }
    protected abstract fun execute(params: P): Result<R>
}