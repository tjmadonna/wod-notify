package com.madonnaapps.wodnotify

import android.app.Application
import com.madonnaapps.wodnotify.di.AppComponent
import com.madonnaapps.wodnotify.di.AppComponentImpl

class WodApplication : Application() {

    val appComponent: AppComponent = AppComponentImpl(this)

}