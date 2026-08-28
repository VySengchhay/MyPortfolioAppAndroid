package com.androidapp.myportfolioappandroid.core.util

import android.util.Patterns

object ValidationUtil {
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Name is required"
            name.trim().length < 2 -> "Name must be at least 2 characters"
            !name.trim().matches(Regex("^[a-zA-Z ]+$")) ->
                "Name can only contain letters and spaces"
            else -> null
        }
    }

    fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() ->
                "Invalid email address"
            else -> null
        }
    }
}