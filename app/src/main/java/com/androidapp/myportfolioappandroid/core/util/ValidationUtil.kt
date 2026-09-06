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

    fun validatePrice(price: String): String? {
        val priceValue = price.trim().toDoubleOrNull()

        return when {
            price.isBlank() ->
                "Price is required"

            priceValue == null ->
                "Enter a valid number"

            priceValue <= 0.0 ->
                "Price must be greater than 0"

            priceValue > 1_000_000.0 ->
                "Price must not exceed 1,000,000"

            else -> null
        }
    }

    fun validateCategory(category: String): String? {
        return when {
            category.isBlank() ->
                "Category is required"

            else -> null
        }
    }

    fun validateImage(image: String): String? {
        return when {
            image.isBlank() ->
                "Image URL is required"

            !Patterns.WEB_URL.matcher(image.trim()).matches() ->
                "Enter a valid image URL"

            else -> null
        }
    }
}