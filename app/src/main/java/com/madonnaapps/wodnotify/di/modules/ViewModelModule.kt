package com.madonnaapps.wodnotify.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madonnaapps.wodnotify.di.keys.ViewModelKey
import com.madonnaapps.wodnotify.ui.wods.WodsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WodsViewModel::class)
    abstract fun bindWodsViewModel(wodsViewModel: WodsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        viewModelProviderFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory

}


/**
 * Factory for providing view models in the app
 * @param creators a map mapping view model type to the view model provider given by Dagger. This
 * is provided by Dagger's multibinding
 */
@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var creator: Provider<out ViewModel>? = creators[modelClass]

        if (creator == null) {
            // Loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        // If this is not one of the allowed keys, throw exception
        if (creator == null) throw IllegalStateException("Unknown model class: $modelClass")

        // Return the Provider
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}