package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.ParticipantsListFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsList.SponsorAdapter
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsList.SponsorsListFragmentArgs
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsList.SponsorsListFragmentDirections
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantsListFragment : BaseSupportFragment() {

    override val viewModel by viewModel<ParticipantsListViewModel>()

    private lateinit var binding: ParticipantsListFragmentBinding
    private lateinit var participantAdapter: ParticipantAdapter
    private val args by navArgs<ParticipantsListFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ParticipantsListFragmentBinding.inflate(inflater, container, false)
        binding.toolbarLayout.tvTitle.text = "Participants"
        requireActivity().navigationBarAndStatusBarColor(R.color.darkBarColor, R.color.darkBarColor)

        setupRecyclerView()
        onClickListener()
        addCallBackToExit()

        args.event?.let {
            participantAdapter.differ.submitList(it.participates)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        participantAdapter = ParticipantAdapter()
        binding.rvParticipantsList.apply {
            adapter = participantAdapter
        }
    }

    private fun onClickListener(){
        binding.toolbarLayout.backButton.setOnClickListener {
            navigateToEventDetails()
        }
        participantAdapter.setOnClickListener {
            navController.navigate(ParticipantsListFragmentDirections.actionParticipantsListFragmentToParticipantBioFragment(it, args.event))
        }
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }


    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        @SuppressLint("RestrictedApi")
        override fun handleOnBackPressed() {
            navigateToEventDetails()
        }
    }

    private fun navigateToEventDetails() {
        navController.navigate(ParticipantsListFragmentDirections.actionParticipantsListFragmentToEventDetailsFragment(args.event))
    }

}