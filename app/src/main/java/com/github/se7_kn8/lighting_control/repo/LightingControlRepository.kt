package com.github.se7_kn8.lighting_control.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.se7_kn8.lighting_control.DefaultCallback
import com.github.se7_kn8.lighting_control.ErrorHandler
import com.github.se7_kn8.lighting_control.entity.Color
import com.github.se7_kn8.lighting_control.service.ControlService

class LightingControlRepository(private val service: ControlService) {

    fun getCurrentColor(errorHandler: ErrorHandler): LiveData<Color> {
        val data = MutableLiveData<Color>()
        service.currentColor().enqueue(DefaultCallback<String>(errorHandler) { data.value = Color(it) })
        return data
    }

    fun getVersion(errorHandler: ErrorHandler): LiveData<String> {
        val data = MutableLiveData<String>()
        service.version().enqueue(DefaultCallback<String>(errorHandler) { data.value = it })
        return data
    }

    fun setColor(color: Color, errorHandler: ErrorHandler) {
        service.setColor(color.toHexString(), 1000).enqueue(DefaultCallback<Unit>(errorHandler))
    }

    fun resetColor(errorHandler: ErrorHandler): LiveData<Color> {
        val data = MutableLiveData<Color>()
        service.reset(1000).enqueue(DefaultCallback<String>(errorHandler) { data.value = Color(it) })
        return data
    }

    fun stop(errorHandler: ErrorHandler) {
        service.stopMode().enqueue(DefaultCallback<Unit>(errorHandler))
    }

    fun saveAsDefault(color: Color, errorHandler: ErrorHandler) {
        service.setDefaultColor(color.toHexString()).enqueue(DefaultCallback<Unit>(errorHandler))
    }

    fun getModes(errorHandler: ErrorHandler): LiveData<Array<String>> {
        val data = MutableLiveData<Array<String>>()
        service.getModes().enqueue(DefaultCallback<String>(errorHandler) { data.value = it.split(", ").toTypedArray() })
        return data
    }

    fun start(mode: String, multiplier: Float, errorHandler: ErrorHandler) {
        service.setMode(mode).enqueue(DefaultCallback<Unit>(errorHandler) {
            service.startMode(multiplier).enqueue(DefaultCallback<Unit>(errorHandler))
        })
    }

    fun getMode(errorHandler: ErrorHandler): LiveData<String> {
        val data = MutableLiveData<String>()
        service.getCurrentMode().enqueue(DefaultCallback<String>(errorHandler) { data.value = it })
        return data
    }

}