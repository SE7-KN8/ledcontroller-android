package com.github.se7_kn8.lighting_control.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

open class ObservableViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

}