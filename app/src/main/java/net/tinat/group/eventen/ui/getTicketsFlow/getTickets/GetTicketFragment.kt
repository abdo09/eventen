package net.tinat.group.eventen.ui.getTicketsFlow.getTickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.GetTicketFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class GetTicketFragment : BaseSupportFragment() {

    override val viewModel by viewModel<GetTicketsViewModel>()

    private lateinit var binding: GetTicketFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GetTicketFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}