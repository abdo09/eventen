package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsList

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
import net.tinat.group.eventen.databinding.SponsorsListFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.home.adapters.featuredThisWeek.EventAdapter
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.EventDetailsFragmentArgs
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList.ParticipantsListFragmentDirections
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList.SponsorsListViewModel
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio.SponsorBioFragmentDirections
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class SponsorsListFragment : BaseSupportFragment() {

    override val viewModel by viewModel<SponsorsListViewModel>()

    private lateinit var binding: SponsorsListFragmentBinding
    private lateinit var sponsorListAdapter: SponsorAdapter
    private val args by navArgs<SponsorsListFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SponsorsListFragmentBinding.inflate(inflater, container, false)
        binding.toolbarLayout.tvTitle.text = "Sponsors"
        requireActivity().navigationBarAndStatusBarColor(R.color.darkBarColor, R.color.darkBarColor)

        setupRecyclerView()
        onClickListener()
        addCallBackToExit()

        args.event?.let {
            sponsorListAdapter.differ.submitList(it.sponsors)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        sponsorListAdapter = SponsorAdapter()
        binding.rvSponsorsList.apply {
            adapter = sponsorListAdapter
        }
    }

    private fun onClickListener(){
        binding.toolbarLayout.backButton.setOnClickListener {
            navigateToEventDetails()
        }
        sponsorListAdapter.setOnClickListener {
            navController.navigate(SponsorsListFragmentDirections.actionSponsorsListFragmentToSponsorBioFragment(it, args.event))
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
        navController.navigate(SponsorsListFragmentDirections.actionSponsorsListFragmentToEventDetailsFragment(args.event))
    }

}