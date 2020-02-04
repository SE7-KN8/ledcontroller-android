package com.github.se7_kn8.lighting_control.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_static_color, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_reset -> viewModel.resetColor()
            R.id.menu_save_default -> {
                AlertDialog.Builder(this.requireContext()).setTitle(R.string.save_as_default_desc)
                    .setPositiveButton(android.R.string.yes) { dialog, _ ->
                        viewModel.saveAsDefault()
                        dialog.dismiss()
                    }.setNegativeButton(android.R.string.no) { dialog, _ -> dialog.dismiss() }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
