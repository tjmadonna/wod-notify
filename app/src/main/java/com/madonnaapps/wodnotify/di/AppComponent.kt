package com.madonnaapps.wodnotify.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.madonnaapps.wodnotify.BuildConfig
import com.madonnaapps.wodnotify.data.WodRepository
import com.madonnaapps.wodnotify.data.WodRepositoryImpl
import com.madonnaapps.wodnotify.data.local.WodLocalDataSource
import com.madonnaapps.wodnotify.data.local.WodLocalDataSourceImpl
import com.madonnaapps.wodnotify.data.local.databases.AppDatabase
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSource
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSourceImpl
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSource
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSourceImpl
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapper
import com.madonnaapps.wodnotify.data.remote.services.WodRemoteService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*

interface AppComponent {

    val wodRepository: WodRepository

}

class AppComponentImpl constructor(
    private val application: Application
) : AppComponent {

    override val wodRepository: WodRepository by lazy {
        WodRepositoryImpl(wodLocalDataSource, wodRemoteDataSource, sharedPreferencesDataSource)
    }

    // Local Data

    private val localDatabase by lazy {
        AppDatabase(application)
    }

    private val wodLocalDataSource: WodLocalDataSource by lazy {
        WodLocalDataSourceImpl(localDatabase.wodDao())
    }

    // Remote Data

    private val wodRemoteService by lazy {
        Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WodRemoteService::class.java)
    }

    private val wodRemoteMapper by lazy {
        WodNetworkResponseItemMapper(
            arrayOf(SimpleDateFormat("MMddyyyy", Locale.US)),
            BuildConfig.BASE_URL
        )
    }

    private val wodRemoteDataSource: WodRemoteDataSource by lazy {
        WodRemoteDataSourceImpl(wodRemoteService, wodRemoteMapper)
    }

    // Shared Preferences

    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    private val sharedPreferencesDataSource: SharedPreferencesDataSource by lazy {
        SharedPreferencesDataSourceImpl(
            sharedPreferences
        )
    }
}