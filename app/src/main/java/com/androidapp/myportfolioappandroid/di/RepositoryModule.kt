package com.androidapp.myportfolioappandroid.di

import com.androidapp.myportfolioappandroid.feature.apifeature.data.repository.UserRepositoryImpl
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}