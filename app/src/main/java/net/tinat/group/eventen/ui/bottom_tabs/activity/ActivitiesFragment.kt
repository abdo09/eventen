package net.tinat.group.eventen.ui.bottom_tabs.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ActivitiesFragment : BaseSupportFragment(R.layout.activities_fragment) {

    override val viewModel by viewModel<ActivitiesViewModel>()

    override var navigationVisibility = View.VISIBLE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}