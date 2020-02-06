package com.github.se7_kn8.lighting_control.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.github.se7_kn8.lighting_control.R
import com.github.se7_kn8.lighting_control.databinding.FragmentGpioControlBinding
import com.github.se7_kn8.lighting_control.makeErrorSnackbar
import com.github.se7_kn8.lighting_control.viewmodel.GpioControlViewModel
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class GpioControlFragment : Fragment() {

    private val viewModel by inject<GpioControlViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGpioControlBinding>(inflater, R.layout.fragment_gpio_control, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        makeErrorSnackbar(viewModel, binding.root, this)

        return binding.root
    }

}
