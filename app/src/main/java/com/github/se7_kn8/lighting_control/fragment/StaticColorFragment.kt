package com.github.se7_kn8.lighting_control.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory

import com.github.se7_kn8.lighting_control.R
import com.github.se7_kn8.lighting_control.databinding.FragmentStaticColorBinding
import com.github.se7_kn8.lighting_control.viewmodel.StaticColorViewModel

class StaticColorFragment : Fragment() {

    private val viewModel by viewModels<StaticColorViewModel> { SavedStateViewModelFactory(requireActivity().application, this, Bundle().apply {
        this.putInt("red", 0)
        this.putInt("green", 0)
        this.putInt("blue", 0)
    }) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentStaticColorBinding>(inflater, R.layout.fragment_static_color, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

}
