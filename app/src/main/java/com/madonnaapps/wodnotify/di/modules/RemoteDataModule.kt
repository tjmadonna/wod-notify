package com.madonnaapps.wodnotify.di.modules

import com.madonnaapps.wodnotify.BuildConfig
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSource
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSourceImpl
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapper
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapperImpl
import com.madonnaapps.wodnotify.data.remote.services.WodRemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
abstract class RemoteDataModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideMoshiConverterFactory(): MoshiConverterFactory {
            return MoshiConverterFactory.create()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
            return Retrofit.Builder()
                .baseUrl("${BuildConfig.BASE_URL}/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideWodRemoteService(retrofit: Retrofit): WodRemoteService {
            return retrofit.create(WodRemoteService::class.java)
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideWodNetworkResponseItemMapper(): WodNetworkResponseItemMapper {
            return WodNetworkResponseItemMapperImpl(
                arrayOf(SimpleDateFormat("MMddyyyy", Locale.US)),
                BuildConfig.BASE_URL
            )
        }

    }

    @Binds
    abstract fun bindWodRemoteDataSource(
        wodRemoteDataSourceImpl: WodRemoteDataSourceImpl
    ): WodRemoteDataSource

}