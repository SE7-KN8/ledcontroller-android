package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.se7_kn8.lighting_control.entity.State
import com.github.se7_kn8.lighting_control.repo.GpioControlRepository

class GpioControlViewModel(private val repo: GpioControlRepository) : ErrorViewModel() {

    val pinInput = MutableLiveData<String>("")

    fun writeLow(view: View) {
        repo.writeGPIO(getPin(), State.LOW, errorHandler)
    }

    fun writeHigh(view: View) {
        repo.writeGPIO(getPin(), State.HIGH, errorHandler)
    }

    private fun getPin(): Int {
        if (pinInput.value != null && pinInput.value != "") {
            return pinInput.value!!.toInt()
        }

        return -1
    }

}