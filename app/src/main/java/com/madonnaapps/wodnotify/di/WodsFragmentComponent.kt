package com.madonnaapps.wodnotify.di

import com.madonnaapps.wodnotify.di.scopes.FragmentScope
import com.madonnaapps.wodnotify.ui.wods.WodsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface WodsFragmentComponent {

    // Factory to create instance of WodsFragmentComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): WodsFragmentComponent
    }

    // Classes that can be injected by this component
    fun inject(fragment: WodsFragment)

}
