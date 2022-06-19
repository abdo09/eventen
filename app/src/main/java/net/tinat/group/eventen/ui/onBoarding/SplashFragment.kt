package net.tinat.group.eventen.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.SplashFragmentBinding
import net.tinat.group.eventen.ui.user.login.LoginFragmentViewModel
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseSupportFragment() {


    override val viewModel by viewModel<LoginFragmentViewModel>()

    private var binding: SplashFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationVisibility = View.GONE

        requireActivity().navigationBarAndStatusBarColor(R.color.white, R.color.white, lightFlag = true)

        motionLayoutHandler()

    }

    private fun navigateToNextDestination() {
        if (Constants().getUid(requireContext()).isNullOrEmpty()){
            navController.navigate(R.id.action_splash_to_onBoardingViewPager)
        } else {
            navController.navigate(R.id.action_splash_to_homeFragment)
        }
    }

    private fun motionLayoutHandler() {
        binding?.splashScreen?.addTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) { navigateToNextDestination() }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}