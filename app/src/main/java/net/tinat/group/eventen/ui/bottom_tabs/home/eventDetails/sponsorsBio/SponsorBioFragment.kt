package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.sponsorsBio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.SponsorBioFragmentBinding
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class SponsorBioFragment : BaseSupportFragment() {

    override val viewModel by viewModel<SponsorBioViewModel>()

    private lateinit var binding: SponsorBioFragmentBinding
    private val args by navArgs<SponsorBioFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SponsorBioFragmentBinding.inflate(inflater, container, false)
        requireActivity().navigationBarAndStatusBarColor(R.color.cyan_200, R.color.cyan_200, lightFlag = true)

        binding.backButton.setOnClickListener {
            //navController.navigate(EventDetailsFragmentDirections.actionEventDetailsFragmentToHomeFragment())
        }

        addCallBackToExit()

        args.sponsor?.let {
            binding.sponsor = it
        }

        onClickListener()

        return binding.root
    }

    private fun onClickListener() {
        binding.btnReadMoreAboutEventBioFragment.setOnClickListener {
            binding.viewModel = viewModel
            viewModel.isExtended = !viewModel.isExtended
        }
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }


    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        @SuppressLint("RestrictedApi")
        override fun handleOnBackPressed() {
            val previous = navController.previousBackStackEntry?.destination?.label.toString()
            if (previous == "sponsors_list_fragment")
                navController.navigate(SponsorBioFragmentDirections.actionSponsorBioFragmentToSponsorsListFragment(event = args.event))
            else
                navController.navigate(SponsorBioFragmentDirections.actionSponsorBioFragmentToEventDetailsFragment(event = args.event))
        }

    }

}