package com.madonnaapps.wodnotify.work

import android.content.Context
import androidx.work.*
import com.madonnaapps.wodnotify.work.worker.WodSyncWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

interface WodWorkManager {

    fun createWodPeriodicWork()

}

@Singleton
class WodWorkManagerImpl @Inject constructor(private val context: Context): WodWorkManager {

    companion object {
        private const val WOD_WORK_NAME = "WodSyncWorker"
    }

    override fun createWodPeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresDeviceIdle(false)
            .setRequiresCharging(false)
            .build()

        val request = PeriodicWorkRequestBuilder<WodSyncWorker>(1, TimeUnit.HOURS, 20, TimeUnit.MINUTES)
            .setInitialDelay(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(WOD_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request)
    }

}