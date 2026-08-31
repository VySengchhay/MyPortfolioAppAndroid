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

    fun validateTitle(title: String): String? {
        return when {
            title.isBlank() ->
                "Title is required"

            title.trim().length < 3 ->
                "Title must be at least 3 characters"

            title.trim().length > 100 ->
                "Title must not exceed 100 characters"

            else -> null
        }
    }

    fun validateDescription(description: String): String? {
        return when {
            description.isBlank() ->
                "Description is required"

            description.trim().length < 5 ->
                "Description must be at least 5 characters"

            description.trim().length > 500 ->
                "Description must not exceed 500 characters"

            else -> null
        }
    }
}