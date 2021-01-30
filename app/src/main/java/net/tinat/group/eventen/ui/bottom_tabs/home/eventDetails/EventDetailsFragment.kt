package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails

import android.os.Bundle
import android.view.View
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel


class EventDetailsFragment : BaseSupportFragment(R.layout.event_details_fragment) {

    override val viewModel by viewModel<EventDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}