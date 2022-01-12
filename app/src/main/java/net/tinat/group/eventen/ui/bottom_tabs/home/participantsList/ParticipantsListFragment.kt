package net.tinat.group.eventen.ui.bottom_tabs.home.participantsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.ParticipantsListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantsListFragment : BaseSupportFragment() {

    override val viewModel by viewModel<ParticipantsListViewModel>()

    private lateinit var binding: ParticipantsListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ParticipantsListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}