package net.tinat.group.eventen.ui.bottom_tabs.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportFragment
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.databinding.HomeFragmentBinding
import net.tinat.group.eventen.ui.bottom_tabs.home.adapters.featuredThisWeek.EventAdapter
import net.tinat.group.eventen.utils.formatTime
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseSupportFragment(), PopularEventController.AdapterCallbacks {

    override val viewModel by viewModel<HomeFragmentViewModel>()

    override var navigationVisibility = View.VISIBLE

    private var binding: HomeFragmentBinding? = null
    private lateinit var eventAdapter: EventAdapter

    private val popularEventController: PopularEventController by lazy { PopularEventController(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        addCallBackToExit()

        requireActivity().navigationBarAndStatusBarColor(R.color.purple_700, R.color.purple_700)

        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        setupRecyclerView()

        val popularEvent = viewModel.events().filter { it.popular?: false }
        binding?.event = viewModel.events().filter { it.popular?: false }[0]

        popularEventController.popularEventList = popularEvent
        popularEventController.requestModelBuild()

        eventAdapter.differ.submitList(viewModel.events())

        initController()

        onClickLister()

    }

    private fun initController() {
        binding?.rvPopularEventModel?.setControllerAndBuildModels(controller = popularEventController)
    }

    private fun initViewModel() {
        viewModel.getCategories()?.observe(viewLifecycleOwner) {

        }

        popularEventController.sliderPosition.observe(viewLifecycleOwner){ position ->
            binding?.eventNull = position?.equals(null)
            position?.let { pos ->
                binding?.event = viewModel.events().filter { it.popular?: false }[pos]
            }
        }
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter()
        binding?.rvFeaturedThisWeek?.apply {
            adapter = eventAdapter
        }
    }

    private fun onClickLister(){
        eventAdapter.setOnClickListener { event ->
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(event))
        }
    }

    override fun onPopularEventClickLister(popularEvent: Event) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(popularEvent))
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }

    var lastCallback: Long = 0
    private var callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - lastCallback < 3000) {
                requireActivity().finish()
            } else {
                viewModel.showInfo.value = R.string.press_back_again_to_exit
                lastCallback = System.currentTimeMillis()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvPopularEventModel?.adapter = null
        binding?.rvFeaturedThisWeek?.adapter = null
        binding = null
    }

}