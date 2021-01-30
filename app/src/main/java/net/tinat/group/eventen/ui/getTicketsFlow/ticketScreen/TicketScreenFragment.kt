package net.tinat.group.eventen.ui.getTicketsFlow.ticketScreen

import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TicketScreenFragment : BaseSupportFragment(R.layout.ticket_screen_fragment) {

    override val viewModel by viewModel<TicketScreenViewModel>()


}