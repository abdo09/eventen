package net.tinat.group.eventen.ui.bottom_tabs.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.TicketsFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.activity.ActivitiesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TicketsFragment : BaseSupportFragment() {

    override val viewModel by viewModel<TicketsViewModel>()

    override var navigationVisibility = View.VISIBLE

    private var binding: TicketsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TicketsFragmentBinding.inflate(inflater, container, false)

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