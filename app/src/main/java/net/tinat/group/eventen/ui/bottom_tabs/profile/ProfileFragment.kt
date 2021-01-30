package net.tinat.group.eventen.ui.bottom_tabs.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseSupportFragment(R.layout.profile_fragment) {

    override val viewModel by viewModel<ProfileViewModel>()

    override var navigationVisibility = View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().navigationBarAndStatusBarColor(R.color.purple_900, R.color.purple_900)
    }

}