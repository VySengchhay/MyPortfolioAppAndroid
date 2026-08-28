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

fun AppError.toMessage(): String {
    return when (this) {
        is AppError.BadRequest ->
            "Invalid request"

        is AppError.Unauthorized ->
            "Unauthorized"

        is AppError.NotFound ->
            "Users not found"

        is AppError.Server ->
            "Server error"

        is AppError.Network ->
            "No internet connection"

        is AppError.Timeout ->
            "Request timed out"

        is AppError.Http ->
            "Request failed (${statusCode})"

        is AppError.Unknown ->
            cause?.message ?: "Something went wrong"
    }
}