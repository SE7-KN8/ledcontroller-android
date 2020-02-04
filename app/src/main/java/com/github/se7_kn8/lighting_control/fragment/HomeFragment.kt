package com.github.se7_kn8.lighting_control.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.github.se7_kn8.lighting_control.R
import com.github.se7_kn8.lighting_control.databinding.FragmentHomeBinding
import com.github.se7_kn8.lighting_control.makeErrorSnackbar
import com.github.se7_kn8.lighting_control.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel by inject<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.staticColorClickHandler = View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_staticColorFragment)
        }
        binding.modeClickHandler = View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_modeFragment)
        }
        makeErrorSnackbar(viewModel, binding.root, this)
        return binding.root
    }
    
}
