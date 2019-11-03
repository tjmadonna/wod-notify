package com.madonnaapps.wodnotify.di.modules

import com.madonnaapps.wodnotify.data.WodRepository
import com.madonnaapps.wodnotify.data.WodRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindWodRepository(wodRepositoryImpl: WodRepositoryImpl): WodRepository

}