package com.github.se7_kn8.lighting_control.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ErrorViewModel: ViewModel() {

    val error = MutableLiveData<Throwable>()

}