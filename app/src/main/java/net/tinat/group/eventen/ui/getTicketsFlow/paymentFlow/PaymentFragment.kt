package net.tinat.group.eventen.ui.getTicketsFlow.paymentFlow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.databinding.PaymentFragmentBinding
import net.tinat.group.eventen.ui.getTicketsFlow.getTickets.GetTicketFragmentArgs
import net.tinat.group.eventen.utils.drawable
import org.koin.android.viewmodel.ext.android.viewModel

class  PaymentFragment : BaseSupportFragment(){

    override val viewModel by viewModel<PaymentViewModel>()

    private var binding: PaymentFragmentBinding? = null
    private val args by navArgs<PaymentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentFragmentBinding.inflate(inflater, container, false)

        initViews()
        setupSpinner()
        onClickListener()

        return binding?.root
    }

    private fun onClickListener() {
        binding?.apply {
            btnPay.setOnClickListener {
                navController.navigate(PaymentFragmentDirections.actionPaymentFragmentToTicketScreenFragment(event = args.event))
            }

            toolbarLayout.backButton.setOnClickListener {
                navController.navigate(PaymentFragmentDirections.actionPaymentFragmentToGetTicketFragment(event = args.event))
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding?.toolbarLayout?.tvTitle?.text = "Payment method"
    }

    private fun setupSpinner(){
        binding?.spYear?.setPopupBackgroundDrawable(drawable("#FFFFFF", 8, 8, 8, 8))
        binding?.spMonth?.setPopupBackgroundDrawable(drawable("#FFFFFF", 8, 8, 8, 8))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}