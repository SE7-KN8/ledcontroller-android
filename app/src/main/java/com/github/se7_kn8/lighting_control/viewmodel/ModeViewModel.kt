package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository
import kotlin.math.pow

class ModeViewModel(private val repo: LightingControlRepository) : ErrorViewModel() {

    var progress = MutableLiveData(4)

    private val modes = repo.getModes { error.value = it }

    val currentMode = repo.getMode { error.value = it }

    val currentModes = MediatorLiveData<Array<String>>().apply {
        value = Array(0) { "" }
        addSource(modes) {
            value = it
            removeSource(modes)
        }
    }
    val selectedItem = MediatorLiveData<Int>().apply { value = 0 }


    fun setMode(view: View) {
        if (currentModes.value != null && selectedItem.value != null) {
            repo.setMode(currentModes.value!![selectedItem.value!!]) { error.value = it }
        }
    }

    fun start(view: View) {
        repo.start(2f.pow(progress.value!!.toFloat() - 4f)) { error.value = it }
    }

    fun stop(view: View) {
        repo.stop { error.value = it }
    }

}