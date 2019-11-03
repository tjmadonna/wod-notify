package com.madonnaapps.wodnotify

import android.app.Application
import com.madonnaapps.wodnotify.di.AppComponent
import com.madonnaapps.wodnotify.di.DaggerAppComponent

class WodApplication : Application() {

    // Instance of the app component that will be used by all
    val appComponent: AppComponent by lazy {
        return@lazy initializeAppComponent()
    }

    private fun initializeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}