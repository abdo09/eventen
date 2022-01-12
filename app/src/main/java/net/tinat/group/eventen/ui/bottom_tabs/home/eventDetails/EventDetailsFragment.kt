package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.event_details_fragment.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.EventDetailsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class EventDetailsFragment : BaseSupportFragment() {

    override val viewModel by viewModel<EventDetailsViewModel>()

    private lateinit var binding: EventDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appBarLayout.toolbar_layout.tv_title.text = resources.getString(R.string.besudan_event)
    }
}