package net.tinat.group.eventen.ui.getTicketsFlow.getTickets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.databinding.GetTicketFragmentBinding
import net.tinat.group.eventen.utils.drawable
import net.tinat.group.eventen.utils.getDoubleValue
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class GetTicketFragment : BaseSupportFragment(), AdapterView.OnItemSelectedListener {


    override val viewModel by viewModel<GetTicketsViewModel>()
    private var binding: GetTicketFragmentBinding? = null
    private val args by navArgs<GetTicketFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GetTicketFragmentBinding.inflate(inflater, container, false)

        args.event?.let {
            binding?.event = it
        }

        binding?.toolbarLayout?.tvTitle?.text = "Ticket Type"
        requireActivity().navigationBarAndStatusBarColor(R.color.darkBarColor, R.color.darkBarColor)

        setupSpinner()
        onClickListener()
        addCallBackToExit()

        return binding?.root
    }

    private fun onClickListener() {
        binding?.apply {
            btnPlus.setOnClickListener {
                plusOrMinusOperation(OperationType.PLUS)
            }

            btnMinus.setOnClickListener {
                plusOrMinusOperation(OperationType.MINUS)
            }

            toolbarLayout.backButton.setOnClickListener {
                navController.navigate(GetTicketFragmentDirections.actionGetTicketFragmentToEventDetailsFragment(args.event))
            }

            btnProceedToPay.setOnClickListener {
                navController.navigate(GetTicketFragmentDirections.actionGetTicketFragmentToPaymentFragment(event = args.event))
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        setTicketType(pos)
        setPrice()
    }

    private fun setTicketType(pos: Int) {
        when(pos){
            0 -> args.event?.ticketsType = Event.TicketsType.VIP
            1 -> args.event?.ticketsType = Event.TicketsType.VISITOR
        }
    }

    private fun setupSpinner(){
        binding?.spTickets?.onItemSelectedListener = this
        binding?.spTickets?.setPopupBackgroundDrawable(drawable("#A5E5D9", 8, 8, 8, 8))

        setPrice()
    }

    private fun setPrice() {
        val quantity = binding?.tvQuantity?.text.toString().toInt()
        ticketPrice(quantity = quantity)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun plusOrMinusOperation(operationType: OperationType){
        when(operationType){
            OperationType.PLUS -> {
                var quantity = binding?.tvQuantity?.text.toString().toInt()
                if (quantity < 5) quantity += 1
                binding?.tvQuantity?.text = quantity.toString()
                ticketPrice(quantity = quantity)
            }
            OperationType.MINUS -> {
                var quantity = binding?.tvQuantity?.text.toString().toInt()
                if (quantity > 1) quantity -= 1
                binding?.tvQuantity?.text = quantity.toString()
                ticketPrice(quantity = quantity)
            }
        }
    }

    private fun ticketPrice(quantity: Int) {
        var price = ""
        when(binding?.spTickets?.selectedItemPosition){
            0 -> {
                price = getDoubleValue((quantity * 200).toDouble())
            }
            1 -> {
                price = getDoubleValue((quantity * 50).toDouble())
            }
        }
        binding?.tvPrice?.text = price
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }


    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        @SuppressLint("RestrictedApi")
        override fun handleOnBackPressed() {
           navController.navigate(GetTicketFragmentDirections.actionGetTicketFragmentToEventDetailsFragment(event = args.event))
        }

    }

    enum class OperationType{
        PLUS, MINUS
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}