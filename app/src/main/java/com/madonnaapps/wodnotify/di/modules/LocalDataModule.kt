package com.madonnaapps.wodnotify.di.modules

import android.content.Context
import com.madonnaapps.wodnotify.data.local.WodLocalDataSource
import com.madonnaapps.wodnotify.data.local.WodLocalDataSourceImpl
import com.madonnaapps.wodnotify.data.local.daos.WodDao
import com.madonnaapps.wodnotify.data.local.databases.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class LocalDataModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideDatabase(context: Context): AppDatabase {
            return AppDatabase(context)
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideWodDao(database: AppDatabase): WodDao {
            return database.wodDao()
        }

    }

    @Binds
    abstract fun bindWodLocalDataSource(
        wodLocalDataSourceImpl: WodLocalDataSourceImpl
    ): WodLocalDataSource

}