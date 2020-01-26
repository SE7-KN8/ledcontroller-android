package com.github.se7_kn8.lighting_control.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.se7_kn8.lighting_control.entity.Color
import com.github.se7_kn8.lighting_control.service.LightingControlService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

typealias ErrorHandler = (t: Throwable) -> Unit

class LightingControlRepository {

    class DefaultCallback<T>(private val handler: ErrorHandler, private val callback: (T) -> Unit = { _ -> /*NOP by default*/ }) : Callback<T> {

        override fun onFailure(call: Call<T>, t: Throwable) {
            handler(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful && response.body() != null) {
                callback(response.body()!!)
            } else {
                handler(IllegalStateException("Response is invalid"))
            }
        }

    }


    private val service = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/control/").addConverterFactory(ScalarsConverterFactory.create()).build()
        .create(LightingControlService::class.java)

    fun getCurrentColor(errorHandler: ErrorHandler): LiveData<Color> {
        val data = MutableLiveData<Color>()
        service.currentColor().enqueue(DefaultCallback<String>(errorHandler) {
            data.value = Color(it)
        })
        return data
    }

    fun setColor(color: Color, errorHandler: ErrorHandler) {
        service.setColor(color.toHexString(), 1000).enqueue(DefaultCallback<Unit>(errorHandler))
    }

}