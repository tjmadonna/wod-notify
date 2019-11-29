package com.madonnaapps.wodnotify.di

import android.content.Context
import com.madonnaapps.wodnotify.di.modules.*
import com.madonnaapps.wodnotify.ui.MainActivity
import com.madonnaapps.wodnotify.work.worker.WodSyncWorker
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
        SharedPreferencesDataModule::class,
        NotificationModule::class,
        WorkModule::class
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

    // Classes that can inject into component
    fun inject(mainActivity: MainActivity)

    fun inject(wodSyncWorker: WodSyncWorker)

}