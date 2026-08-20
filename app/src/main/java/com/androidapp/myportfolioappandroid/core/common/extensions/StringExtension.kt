package com.androidapp.myportfolioappandroid.core.common.extensions

fun String.nameFromEmail(): String {
    return substringBefore("@")
}
