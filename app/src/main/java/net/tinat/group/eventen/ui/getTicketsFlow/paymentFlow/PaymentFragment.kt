package net.tinat.group.eventen.ui.getTicketsFlow.paymentFlow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : BaseSupportFragment(R.layout.payment_fragment) {

    override val viewModel by viewModel<PaymentViewModel>()

}