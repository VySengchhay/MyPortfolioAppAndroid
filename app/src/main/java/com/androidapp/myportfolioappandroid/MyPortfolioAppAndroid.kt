package com.androidapp.myportfolioappandroid

import android.app.Application
import com.androidapp.myportfolioappandroid.core.service.fms.NotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyPortfolioAppAndroid : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}