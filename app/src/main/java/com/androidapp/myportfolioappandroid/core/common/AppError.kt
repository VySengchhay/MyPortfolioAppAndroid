package com.androidapp.myportfolioappandroid.core.common

sealed interface AppError {

    data object BadRequest : AppError

    data object Unauthorized : AppError

    data object NotFound : AppError

    data object Server : AppError

    data object Network : AppError

    data object Timeout : AppError

    data class Http(
        val statusCode: Int
    ) : AppError

    data class Unknown(
        val cause: Throwable? = null
    ) : AppError
}