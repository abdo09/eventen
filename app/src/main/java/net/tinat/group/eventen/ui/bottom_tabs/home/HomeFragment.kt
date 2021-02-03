package net.tinat.group.eventen.ui.bottom_tabs.home

import android.os.Bundle
import android.util.Log
import android.view.View
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseSupportFragment(R.layout.home_fragment) {

    override val viewModel by viewModel<HomeFragmentViewModel>()

    override var navigationVisibility = View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().navigationBarAndStatusBarColor(R.color.purple_700, R.color.purple_700)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        viewModel.getCategories()?.observe(viewLifecycleOwner, { categories ->

        })
    }

}