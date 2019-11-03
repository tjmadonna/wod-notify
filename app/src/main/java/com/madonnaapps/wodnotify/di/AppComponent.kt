package com.madonnaapps.wodnotify.di

import android.content.Context
import com.madonnaapps.wodnotify.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SubComponentModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        LocalDataModule::class,
        RemoteDataModule::class,
        SharedPreferencesDataModule::class
    ]
)
interface AppComponent {

    // Factory to create instance of AppComponent
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Exposed subcomponent factories
    fun wodsFragmentComponentFactory(): WodsFragmentComponent.Factory

}