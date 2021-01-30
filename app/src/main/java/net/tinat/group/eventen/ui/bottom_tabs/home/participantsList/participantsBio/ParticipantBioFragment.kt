package net.tinat.group.eventen.ui.bottom_tabs.home.participantsList.participantsBio

import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantBioFragment : BaseSupportFragment(R.layout.participant_bio_fragment) {

    override val viewModel by viewModel<ParticipantsBioViewModel>()

}