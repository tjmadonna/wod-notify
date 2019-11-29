package com.madonnaapps.wodnotify.notification

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.madonnaapps.wodnotify.R
import javax.inject.Inject

interface WodNotificationManager {

    fun createWodSyncNotificationChannel()

    fun createWodNotification(title: String, pendingIntent: PendingIntent)

}

class WodNotificationManagerImpl @Inject constructor(
    private val context: Context
): WodNotificationManager {

    companion object {
        private const val WOD_SYNC_ID = "wod_sync"
        private const val WOD_NOTIFICATION_ID = 15493
    }

    override fun createWodSyncNotificationChannel()  {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val systemNotificationManager = context.applicationContext
                .getSystemService(android.app.NotificationManager::class.java)

            systemNotificationManager.apply {
                // Register the channel with the system
                // Importance or other notification behaviors cannot be changed after this
                val name = "Wod Sync"
                val description = "Notification shown when a new Wod is posted"
                val importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(WOD_SYNC_ID, name, importance)
                channel.description = description
                createNotificationChannel(channel)
            }
        }

    }

    override fun createWodNotification(title: String, pendingIntent: PendingIntent) {

        val notification = NotificationCompat.Builder(context.applicationContext, "wod_sync")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        with(NotificationManagerCompat.from(context.applicationContext)) {
            // Notification Id is a unique int for each notification that you must define
            notify(WOD_NOTIFICATION_ID, notification)
        }
    }


}