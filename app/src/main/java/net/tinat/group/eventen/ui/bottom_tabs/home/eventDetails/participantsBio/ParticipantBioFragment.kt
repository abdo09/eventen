package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsBio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.ParticipantBioFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio.SponsorBioFragmentDirections
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio.SponsorBioViewModel
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantBioFragment : BaseSupportFragment() {

    override val viewModel by viewModel<ParticipantsBioViewModel>()

    private var binding: ParticipantBioFragmentBinding? = null
    private val args by navArgs<ParticipantBioFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ParticipantBioFragmentBinding.inflate(inflater, container, false)

        requireActivity().navigationBarAndStatusBarColor(
            R.color.cyan_200,
            R.color.cyan_200,
            lightFlag = true
        )

        args.participant?.let {
            binding?.participant = it
        }

        addCallBackToExit()

        onClickListener()

        return binding?.root
    }

    private fun onClickListener() {

        binding?.btnReadMoreAboutEventBioFragment?.setOnClickListener {
            binding?.viewModel = viewModel
            viewModel.extend()
        }

        binding?.apply {
            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }


    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        @SuppressLint("RestrictedApi")
        override fun handleOnBackPressed() {
            onBackPressed()
        }

    }

    private fun onBackPressed() {
        val previous = navController.previousBackStackEntry?.destination?.label.toString()
        if (previous == "participants_list_fragment")
            navController.navigate(
                ParticipantBioFragmentDirections.actionParticipantBioFragmentToParticipantsListFragment(
                    event = args.event
                )
            )
        else
            navController.navigate(
                ParticipantBioFragmentDirections.actionParticipantBioFragmentToEventDetailsFragment(
                    event = args.event
                )
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}