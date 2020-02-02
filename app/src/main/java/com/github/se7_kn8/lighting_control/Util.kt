package com.github.se7_kn8.lighting_control

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.github.se7_kn8.lighting_control.viewmodel.ErrorViewModel
import com.google.android.material.snackbar.Snackbar

fun makeErrorSnackbar(viewModel: ErrorViewModel, view: View, lifecycleOwner: LifecycleOwner) {
    viewModel.error.observe(lifecycleOwner) {
        Snackbar.make(view, "Error: ${it.message}", Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok) {}.show()
    }
}