package net.tinat.group.eventen.ui.bottom_tabs.home.participantsList

import androidx.fragment.app.Fragment
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantsListFragment : BaseSupportFragment(R.layout.participants_list_fragment) {

    override val viewModel by viewModel<ParticipantsListViewModel>()

}