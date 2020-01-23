package com.github.se7_kn8.lighting_control.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.github.se7_kn8.lighting_control.entity.Color
import com.github.se7_kn8.lighting_control.service.LightingControlService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class StaticColorViewModel(state: SavedStateHandle) : ObservableViewModel() {

    val service = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/control/").addConverterFactory(ScalarsConverterFactory.create()).build()
        .create(LightingControlService::class.java)

    var color = MutableLiveData<Color>()


    var red: Int = state.get<Int>("red")!!
        set(value) {
            notifyChange()
            field = value
        }

    var green: Int = state.get<Int>("green")!!
        set(value) {
            notifyChange()
            field = value
        }

    var blue: Int = state.get<Int>("blue")!!
        set(value) {
            notifyChange()
            field = value
        }

    @Bindable
    fun getAndroidColor(): Int {
        return android.graphics.Color.rgb(red, green, blue)
    }

    fun onSend(view: View) {
        service.setColor(Color(red, green,blue).toHexString()).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                println(response.message())
            }

        })
        println("Red: $red, Green: $green, Blue: $blue")
    }

}