package com.madonnaapps.wodnotify.common.extensions

import android.app.Activity
import com.madonnaapps.wodnotify.WodApplication
import com.madonnaapps.wodnotify.di.AppComponent

/**
 * The application component used by Dagger
 */
val Activity.appComponent: AppComponent
    get() = (application as WodApplication).appComponent