package com.madonnaapps.wodnotify.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madonnaapps.wodnotify.R
import com.madonnaapps.wodnotify.common.extensions.appComponent
import com.madonnaapps.wodnotify.notification.WodNotificationManager
import com.madonnaapps.wodnotify.work.WodWorkManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var wodWorkManager: WodWorkManager

    @Inject
    lateinit var wodNotificationManager: WodNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wodNotificationManager.createWodSyncNotificationChannel()
        wodWorkManager.createWodPeriodicWork()
    }

}
