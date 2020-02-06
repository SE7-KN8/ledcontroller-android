package com.github.se7_kn8.lighting_control

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.github.se7_kn8.lighting_control.viewmodel.ErrorViewModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

fun makeErrorSnackbar(viewModel: ErrorViewModel, view: View, lifecycleOwner: LifecycleOwner) {
    viewModel.error.observe(lifecycleOwner) {
        try {
            Snackbar.make(view, "Error: ${it.message}", Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok) {}.show()
        } catch (e: Exception) {
        }
    }
}

typealias ErrorHandler = (t: Throwable) -> Unit

class DefaultCallback<T>(private val handler: ErrorHandler, private val callback: (T) -> Unit = { _ -> /*NOP by default*/ }) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        handler(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful && response.body() != null) {
            try {
                callback(response.body()!!)
            } catch (t: Throwable) {
                handler(t)
            }
        } else {
            handler(IllegalStateException("Response is invalid"))
        }
    }

}