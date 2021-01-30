package net.tinat.group.eventen.ui.getTicketsFlow.getTickets

import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel

class GetTicketFragment : BaseSupportFragment(R.layout.get_ticket_fragment) {
    override val viewModel by viewModel<GetTicketsViewModel>()


}