package com.github.se7_kn8.lighting_control.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.github.se7_kn8.lighting_control.entity.Color
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository

class HomeViewModel(repo: LightingControlRepository) : ErrorViewModel() {

    val version = repo.getVersion { error.value = it }

    private val currentColor = MediatorLiveData<Color>().apply {
        value = Color(0, 0, 0)
        val currentColor = repo.getCurrentColor { error.value = it }
        addSource(currentColor) {
            value = it
            removeSource(currentColor)
        }
    }

    var androidColor = Transformations.map(currentColor) {
        if (it != null) {
            return@map it.toAndroidColor()
        }
        return@map android.graphics.Color.rgb(0, 0, 0)
    }

}