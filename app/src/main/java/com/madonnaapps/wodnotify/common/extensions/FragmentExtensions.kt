package com.madonnaapps.wodnotify.common.extensions

import androidx.fragment.app.Fragment
import com.madonnaapps.wodnotify.WodApplication
import com.madonnaapps.wodnotify.di.AppComponent

/**
 * The application component used by Dagger
 */
val Fragment.appComponent: AppComponent
    get() = (requireActivity().application as WodApplication).appComponent