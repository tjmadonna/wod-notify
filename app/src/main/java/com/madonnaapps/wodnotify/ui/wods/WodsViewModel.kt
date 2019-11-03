package com.madonnaapps.wodnotify.ui.wods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madonnaapps.wodnotify.data.WodRepository
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WodsViewModel @Inject constructor(
    private val wodRepository: WodRepository
) : ViewModel() {

    private val wodsLiveData = MutableLiveData<List<WodEntity>>()
    val wods: MutableLiveData<List<WodEntity>>
        get() = wodsLiveData

    init {
        viewModelScope.launch {
            wodRepository.getAllWods()
                .collect {
                    wodsLiveData.value = it
                }
        }
    }

}