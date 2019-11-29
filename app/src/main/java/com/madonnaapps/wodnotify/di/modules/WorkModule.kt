package com.madonnaapps.wodnotify.di.modules

import com.madonnaapps.wodnotify.work.WodWorkManager
import com.madonnaapps.wodnotify.work.WodWorkManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface WorkModule {

    @Binds
    fun bindWodNotificationManager(wodWorkManager: WodWorkManagerImpl): WodWorkManager

}