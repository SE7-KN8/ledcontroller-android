package com.github.se7_kn8.lighting_control.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.se7_kn8.lighting_control.ErrorHandler

open class ErrorViewModel : ViewModel() {

    val error = MutableLiveData<Throwable>()

    val errorHandler: ErrorHandler = { error.value = it }

}