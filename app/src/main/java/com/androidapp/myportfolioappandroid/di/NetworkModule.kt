package com.androidapp.myportfolioappandroid.di

import com.androidapp.myportfolioappandroid.core.network.ApiConstants
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.ProductApiService
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    @LocalRetrofit
    fun provideLocalRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
        .build()
    }

    @Provides
    @Singleton
    @FakeStoreRetrofit
    fun provideFakeStoreRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.FAKE_STORE_BASE_URL)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
        .build()
    }


    @Provides
    @Singleton
    fun provideUserApi(
        @LocalRetrofit retrofit: Retrofit
    ): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(
        @FakeStoreRetrofit fakeStoreRetrofit: Retrofit
    ): ProductApiService {
        return fakeStoreRetrofit.create(ProductApiService::class.java)
    }
}