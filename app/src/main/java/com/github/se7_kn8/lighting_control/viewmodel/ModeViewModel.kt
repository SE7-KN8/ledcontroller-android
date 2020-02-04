package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository
import kotlin.math.pow

class ModeViewModel(private val repo: LightingControlRepository) : ErrorViewModel() {

    var progress = MutableLiveData(4)

    fun start(view: View) {
        repo.start(2f.pow(progress.value!!.toFloat() - 4f)) { error.value = it }
    }

    fun stop(view: View) {
        repo.stop { error.value = it }
    }

}