package com.androidapp.myportfolioappandroid.di

import jakarta.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeStoreRetrofit