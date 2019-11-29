package com.madonnaapps.wodnotify.di.modules

import com.madonnaapps.wodnotify.notification.WodNotificationManager
import com.madonnaapps.wodnotify.notification.WodNotificationManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface NotificationModule {

    @Binds
    fun bindWodNotificationManager(
        wodNotificationManager: WodNotificationManagerImpl
    ): WodNotificationManager

}