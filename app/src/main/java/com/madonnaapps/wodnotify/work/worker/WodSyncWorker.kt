package com.madonnaapps.wodnotify.work.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.madonnaapps.wodnotify.R
import com.madonnaapps.wodnotify.data.WodRepository
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.di.DaggerAppComponent
import com.madonnaapps.wodnotify.notification.WodNotificationManager
import com.madonnaapps.wodnotify.ui.MainActivity
import javax.inject.Inject

class WodSyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private val appComponent = DaggerAppComponent.factory().create(applicationContext)

    @Inject
    lateinit var wodNotificationManager: WodNotificationManager

    @Inject
    lateinit var wodRepository: WodRepository

    override suspend fun doWork(): Result {
        appComponent.inject(this)

        val newWodsResult = wodRepository.getNewWods()

        if (newWodsResult is com.madonnaapps.wodnotify.common.types.Result.Failure)
            return Result.failure()

        val newWods = (newWodsResult as com.madonnaapps.wodnotify.common.types.Result.Success).data

        if (newWods.isNotEmpty()) {
            createNotification(newWods)
        }

        return Result.success()
    }

    private fun createNotification(newWods: List<WodEntity>) {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val titleParams = if (newWods.size == 1) newWods[0].title else newWods.size.toString()

        val title = applicationContext.resources
            .getQuantityString(R.plurals.wod_notification_title, newWods.size, titleParams)

        val pendingIntent = PendingIntent
            .getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        wodNotificationManager.createWodNotification(title, pendingIntent)
    }

}