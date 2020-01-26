package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import android.widget.SeekBar
import androidx.annotation.ColorRes
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import com.github.se7_kn8.lighting_control.entity.Color
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository
import com.github.se7_kn8.lighting_control.service.LightingControlService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.Exception

class StaticColorViewModel(state: SavedStateHandle) : ObservableViewModel() {

    private val repo = LightingControlRepository()

    var error = MutableLiveData<Throwable>()

    private val currentColor = repo.getCurrentColor { error.value = it }


    var color = MediatorLiveData<Color>().apply {
        value = Color(0, 0, 0)
        addSource(currentColor) {
            value = it
            removeSource(currentColor)
        }
    }

    var androidColor = Transformations.map(color) {
        if (color.value != null) {
            return@map android.graphics.Color.rgb(color.value!!.r, color.value!!.g, color.value!!.b)
        }
        return@map android.graphics.Color.rgb(0, 0, 0)
    }

    fun updateRed(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        color.value = color.value?.changeRed(progress)
    }

    fun updateGreen(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        color.value = color.value?.changeGreen(progress)
    }

    fun updateBlue(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        color.value = color.value?.changeBlue(progress)
    }

    fun onSend(view: View) {
        if (color.value != null) {
            repo.setColor(color.value!!) { error.value = it }
        }
    }

}