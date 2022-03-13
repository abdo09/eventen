package net.tinat.group.eventen.ui.bottom_tabs.home.adapters.model

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import net.tinat.group.eventen.R
import net.tinat.group.eventen.data.dto.Event
import kotlin.coroutines.CoroutineContext

@FlowPreview
@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.popular_event_model)
abstract class SliderCarouselModel(private val sliderPosition: MutableLiveData<Int>) : EpoxyModelWithHolder<SliderCarouselModel.Holder>(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val slidingFlow by lazy {
        flow {
            var x = 0
            do {
                delay(delay)
                emit(x)
                x++
            } while (x < 1000)
        }
    }

    @EpoxyAttribute(hash = false)
    var popularEventList = listOf<Event>()

    @EpoxyAttribute(hash = false)
    var onViewAllPromotionListener: OnModelClickListener<SliderPopularEventModel, View>? = null

    override fun bind(holder: Holder) {

        holder.sliderCarousel.apply {
            (layoutManager as LinearLayoutManager).stackFromEnd = true
            withModels {
                popularEventList.forEachIndexed { index, image ->
                    SliderPopularEventModel_()
                        .id(index)
                        .onClickedItemListener { model, parentView, clickedView, position ->
                            onViewAllPromotionListener?.onClick(model, parentView.eventItem, clickedView, position)
                        }
                        .popularEvent(image)
                        .addTo(this)
                }

                //LoaderModelRow_().id("loading").addIf(imageList.isEmpty() && showLoading,this)
            }
            this.numViewsToShowOnScreen = 1f
            if (popularEventList.size > 1)
                autoScroll(popularEventList.size, this)
            addListener(this)
        }

    }

    private fun addListener(carousel: Carousel) {
        carousel.apply {
            val onScrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    // When dragging, we assume user swiped. So we will stop auto rotation
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        stopAutoScroll()
                    }

                }
            }
            carousel.addOnScrollListener(onScrollListener)
        }
    }

    private val delay = 3000L

    private fun autoScroll(listSize: Int, carousel: Carousel) {
        launch {
            withContext(Dispatchers.Main) {
                slidingFlow.collect {
                    sliderPosition.postValue(it % listSize)
                    carousel.smoothScrollToPosition(it % listSize)
                }
            }
        }
    }

    fun stopAutoScroll() {
        slidingFlow.debounce(delay)
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        stopAutoScroll()
        job.cancel()
    }

    class Holder : EpoxyHolder() {
        lateinit var sliderCarousel: Carousel
        override fun bindView(itemView: View) {
            sliderCarousel = itemView.findViewById(R.id.rv_popular_events)
        }
    }
}