package com.github.se7_kn8.lighting_control.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.github.se7_kn8.lighting_control.R
import com.github.se7_kn8.lighting_control.databinding.FragmentStaticColorBinding
import com.github.se7_kn8.lighting_control.makeErrorSnackbar
import com.github.se7_kn8.lighting_control.viewmodel.StaticColorViewModel
import org.koin.android.ext.android.inject

class StaticColorFragment : Fragment() {

    private val viewModel by inject<StaticColorViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentStaticColorBinding>(inflater, R.layout.fragment_static_color, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        makeErrorSnackbar(viewModel, binding.root, this)

        return binding.root
    }

}
