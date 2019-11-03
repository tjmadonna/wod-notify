package com.madonnaapps.wodnotify.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSource
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class SharedPreferencesDataModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideSharePreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

    }

    @Binds
    abstract fun bindWodRemoteDataSource(
        sharedPreferencesDataSourceImpl: SharedPreferencesDataSourceImpl
    ): SharedPreferencesDataSource

}