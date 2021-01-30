package net.tinat.group.eventen.ui.bottom_tabs.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.ui.bottom_tabs.activity.ActivitiesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TicketsFragment : BaseSupportFragment(R.layout.tickets_fragment) {

    override val viewModel by viewModel<TicketsViewModel>()

    override var navigationVisibility = View.VISIBLE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}