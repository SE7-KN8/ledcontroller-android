package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository
import kotlin.math.pow

class ModeViewModel(private val repo: LightingControlRepository) : ErrorViewModel() {

    var progress = MutableLiveData(5)

    private val modes = repo.getModes { error.value = it }

    // TODO use this
    val currentMode = repo.getMode { error.value = it }

    val currentModes = MediatorLiveData<Array<String>>().apply {
        value = Array(0) { "" }
        addSource(modes) {
            value = it
            removeSource(modes)
        }
    }
    val selectedItem = MediatorLiveData<Int>().apply { value = 0 }

    fun start(view: View) {
        if (currentModes.value != null && selectedItem.value != null) {
            repo.start(currentModes.value!![selectedItem.value!!], 2f.pow(-(progress.value!!.toFloat() - 5f))) { error.value = it }
        }
    }

    fun stop(view: View) {
        repo.stop { error.value = it }
    }

}