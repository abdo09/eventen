package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList.participantsBio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.OnboardingViewpagerBinding
import net.tinat.group.eventen.databinding.ParticipantBioFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantBioFragment : BaseSupportFragment() {

    override val viewModel by viewModel<ParticipantsBioViewModel>()

    private lateinit var binding: ParticipantBioFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ParticipantBioFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}