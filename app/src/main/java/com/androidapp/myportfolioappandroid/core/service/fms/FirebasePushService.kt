package com.androidapp.myportfolioappandroid.core.service.fms

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebasePushService : FirebaseMessagingService() {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(
        remoteMessage: RemoteMessage
    ) {
        super.onMessageReceived(remoteMessage)

        val title =
            remoteMessage.notification?.title
                ?: remoteMessage.data["title"]
                ?: "Notification"

        val body =
            remoteMessage.notification?.body
                ?: remoteMessage.data["body"]
                ?: ""

        val data = remoteMessage.data

        val route = data["route"]
        val transactionId = data["transaction_id"]

        NotificationHelper.showNotification(
            context = this,
            title = title,
            body = body,
            route = route,
            transactionId = transactionId
        )
    }

    override fun onRegistered(
        installationId: String
    ) {
        super.onRegistered(installationId)

        Log.d(
            "FCM",
            "Installation ID: $installationId"
        )

        sendRegistrationToServer(installationId)
    }

    private fun sendRegistrationToServer(
        installationId: String
    ) {
        // Later:
        //
        // notificationRepository.registerDevice(
        //     installationId
        // )
    }
}