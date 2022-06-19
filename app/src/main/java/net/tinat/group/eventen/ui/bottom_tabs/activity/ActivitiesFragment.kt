package net.tinat.group.eventen.ui.bottom_tabs.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.databinding.ActivitiesFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ActivitiesFragment : BaseSupportFragment() {

    override val viewModel by viewModel<ActivitiesViewModel>()

    override var navigationVisibility = View.VISIBLE

    private var binding: ActivitiesFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitiesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}