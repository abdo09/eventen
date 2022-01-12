package net.tinat.group.eventen.ui.getTicketsFlow.paymentFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.databinding.PaymentFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : BaseSupportFragment() {

    override val viewModel by viewModel<PaymentViewModel>()

    private lateinit var binding: PaymentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PaymentFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}