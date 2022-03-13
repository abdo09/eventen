package net.tinat.group.eventen.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.databinding.LoginFragmentBinding
import net.tinat.group.eventen.databinding.OnboardingViewpagerBinding
import net.tinat.group.eventen.databinding.OnboardingViewpagerItemBinding
import net.tinat.group.eventen.ui.user.login.LoginFragmentViewModel
import net.tinat.group.eventen.utils.*
import org.koin.android.viewmodel.ext.android.viewModel

lateinit var bindingOnBoardingStep: OnboardingViewpagerItemBinding

class OnBoardingViewPager : BaseSupportFragment() {

    override val viewModel by viewModel<LoginFragmentViewModel>()

    private lateinit var binding: OnboardingViewpagerBinding

    private val itemCount by lazy { binding.onboardingViewpager.adapter!!.itemCount }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().navigationBarAndStatusBarColor(R.color.purple_700, R.color.purple_700)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OnboardingViewpagerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateViews()

        onClick()

    }

    private fun inflateViews() {
        binding.onboardingViewpager.apply {
            adapter = OnBoardingViewPagerFragmentAdapter(requireActivity())
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handleNextState(position)
                }
            })
        }
        binding.wormDotsIndicator.setViewPager2(binding.onboardingViewpager)
        //onBoardingViewPager.addItemDecoration(CirclePagerIndicatorDecoration())
    }

    private fun setParallaxTransformation(page: View, position: Float) {
        page.apply {
            val parallaxView = bindingOnBoardingStep.onboardingImageView
            when {
                position < -1 -> // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 1f
                position <= 1 -> { // [-1,1]
                    parallaxView.translationX = (-position * (width / 8)).toFloat() //Half the normal speed
                    bindingOnBoardingStep.onboardingHeaderText.translationX = (-position * (width / 4)).toFloat() //Half the normal speed

                    parallaxView.scaleX = (1 - Math.abs(position / 1.92)).toFloat()
                    parallaxView.scaleY = (1 - Math.abs(position / 1.92)).toFloat()
                }
                else -> // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 1f
            }
        }
    }

    private fun handleNextState(index: Int) {
        if (index == itemCount - 1) {
            binding.onboardingBtnNext.fadeOut(200)
            binding.onboardingBtnStart.fadeIn(500)
        } else {
            binding.onboardingBtnNext.fadeOut(200)
            binding.onboardingBtnStart.fadeIn(300)
            if (index == 1)
                context?.getCustomColor(R.color.white)?.let { binding.onboardingBtnNext.toColor(it) }
        }

        if (index == 0) {
            binding.onboardingBtnNext.fadeIn(200)
            binding.onboardingBtnPrevious.fadeOut(200)
            context?.getCustomColor(R.color.cyan_200)?.let { binding.onboardingBtnNext.toColor(it) }
        } else{
            binding.onboardingBtnPrevious.fadeIn(300)
        }

    }

    private fun onClick(){
        binding.onboardingBtnSkip.setOnClickListener {
            navController.navigate(OnBoardingViewPagerDirections.actionOnBoardingViewPagerToLoginFragment())
        }

        binding.onboardingBtnStart.setOnClickListener {
            navController.navigate(OnBoardingViewPagerDirections.actionOnBoardingViewPagerToLoginFragment())
        }

        binding.onboardingBtnNext.setOnClickListener {
            if (binding.onboardingViewpager.currentItem < itemCount - 1)
                binding.onboardingViewpager.setCurrentItemX((binding.onboardingViewpager.currentItem - 1), 300)
        }

        binding.onboardingBtnPrevious.setOnClickListener {
            if (binding.onboardingViewpager.currentItem > 0)
                binding.onboardingViewpager.setCurrentItemX((binding.onboardingViewpager.currentItem + 1), 300)
        }
    }

    class OnBoardingViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): OnBoardingStep {
            return OnBoardingStep.newInstance(position)
        }

        override fun getItemCount(): Int {
            return 3
        }
    }

    class OnBoardingStep : Fragment() {


        private val onBoardingText by lazy { resources.getStringArray(R.array.on_boarding_titles) }
        private val onBoardingIllustrations = listOf(R.drawable.ic_search_cyan, R.drawable.ic_calendar, R.drawable.ic_love)

        companion object {
            fun newInstance(position: Int): OnBoardingStep {
                return OnBoardingStep().apply {
                    arguments = Bundle().apply {
                        putInt("i", position)
                    }
                }
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            bindingOnBoardingStep = OnboardingViewpagerItemBinding.inflate(inflater, container, false)

            val index = requireArguments().getInt("i")
            requireContext().loadWithGlide( into = bindingOnBoardingStep.onboardingImageView, url = ResourcesCompat.getDrawable(resources, onBoardingIllustrations[index], activity?.theme))

            bindingOnBoardingStep.onboardingHeaderText.text =  onBoardingText[index]

            return bindingOnBoardingStep.root

        }
    }
}
