package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.EventDetailsFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.participant.ParticipantImageAdapter
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.sponsor.SponsorImageAdapter
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsBio.ParticipantBioFragmentDirections
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsList.SponsorsListFragmentDirections
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel


class EventDetailsFragment : BaseSupportFragment() {

    override val viewModel by viewModel<EventDetailsViewModel>()

    private var binding: EventDetailsFragmentBinding? = null

    private lateinit var sponsorImageAdapter: SponsorImageAdapter
    private lateinit var participantImageAdapter: ParticipantImageAdapter

    private val args by navArgs<EventDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventDetailsFragmentBinding.inflate(inflater, container, false)

        setupRecyclerView()
        addCallBackToExit()

        args.event.apply {
            this?.let {
                binding?.event = it
                participantImageAdapter.differ.submitList(it.participates)
                sponsorImageAdapter.differ.submitList(it.sponsors)
            }
        }

        requireActivity().navigationBarAndStatusBarColor(R.color.darkBarColor, R.color.darkBarColor)

        onClickLister()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbarLayout?.tvTitle?.text = args.event?.eventName
    }

    private fun onClickLister() {
        binding?.btnExtendEventDescription?.setOnClickListener {
            binding?.viewModel = viewModel
            viewModel.extend()
        }
        binding?.apply {

            btnMoreParticipants.setOnClickListener {
                navController.navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToParticipantsListFragment(
                        args.event
                    )
                )
            }

            btnMoreSponsors.setOnClickListener {
                navController.navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToSponsorsListFragment(
                        args.event
                    )
                )
            }

            toolbarLayout.backButton.setOnClickListener {
                navigateToHomeFragment()
            }

            participantImageAdapter.setOnClickListener {
                navController.navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToParticipantBioFragment(
                        it,
                        args.event
                    )
                )
            }

            sponsorImageAdapter.setOnClickListener {
                navController.navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToSponsorBioFragment(
                        it,
                        args.event
                    )
                )
            }

            btnGetTickets.setOnClickListener {
                navController.navigate(
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToGetTicketFragment(
                        args.event
                    )
                )
            }
        }
    }

    private fun setupRecyclerView() {
        participantImageAdapter = ParticipantImageAdapter()
        sponsorImageAdapter = SponsorImageAdapter()
        binding?.apply {
            rvParticipants.apply {
                adapter = participantImageAdapter
            }

            rvSponsors.apply {
                adapter = sponsorImageAdapter
            }
        }
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }


    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        @SuppressLint("RestrictedApi")
        override fun handleOnBackPressed() {
            navigateToHomeFragment()
        }
    }

    private fun navigateToHomeFragment() {
        navController.navigate(EventDetailsFragmentDirections.actionGlobalHomeFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.apply {
            rvParticipants.adapter = null
            rvSponsors.adapter = null
        }
        binding = null
    }

}