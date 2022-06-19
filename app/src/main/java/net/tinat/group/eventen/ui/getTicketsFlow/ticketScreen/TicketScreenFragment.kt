package net.tinat.group.eventen.ui.getTicketsFlow.ticketScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.TicketScreenFragmentBinding
import net.tinat.group.eventen.ui.getTicketsFlow.paymentFlow.PaymentFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel

class TicketScreenFragment : BaseSupportFragment() {

    override val viewModel by viewModel<TicketScreenViewModel>()

    private var binding: TicketScreenFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TicketScreenFragmentBinding.inflate(inflater, container, false)
        onClickListener()
        return binding?.root
    }

    private fun onClickListener() {
        binding?.closeFragment?.setOnClickListener {
            navController.navigate(
                TicketScreenFragmentDirections.actionTicketScreenFragmentToHomeFragment()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}