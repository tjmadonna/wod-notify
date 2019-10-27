package com.madonnaapps.wodnotify.di

import com.madonnaapps.wodnotify.WodApplication
import com.madonnaapps.wodnotify.data.WodRepository
import com.madonnaapps.wodnotify.ui.wods.WodsFragment
import com.madonnaapps.wodnotify.ui.wods.WodsViewModel

interface WodsFragmentComponent {

    val wodRepository: WodRepository

    val viewModel: WodsViewModel

}

class WodsFragmentComponentImpl(
    private val fragment: WodsFragment
) : WodsFragmentComponent, AppComponent {

    override val wodRepository: WodRepository
        get() {
            val app = fragment.activity?.application as? WodApplication
            return app?.appComponent?.wodRepository
                ?: throw IllegalArgumentException("Application must be of type WodApplication")
        }

    override val viewModel: WodsViewModel
        get() = WodsViewModel(wodRepository)

}