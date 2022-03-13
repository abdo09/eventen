package net.tinat.group.eventen.ui.bottom_tabs.home

import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.AsyncEpoxyController
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.ui.bottom_tabs.home.adapters.model.SliderCarouselModel_
import okhttp3.internal.immutableListOf

class PopularEventController(private val callbacks: AdapterCallbacks) : AsyncEpoxyController() {

    var sliderPosition = MutableLiveData<Int?>()
    var popularEventList = listOf<Event>()

    override fun buildModels() {

        SliderCarouselModel_(sliderPosition)
            .id("SliderCarouselModel_")
            .popularEventList(popularEventList)
            .onViewAllPromotionListener { model, _, _, _ ->
                callbacks.onPopularEventClickLister(model.popularEvent)
            }
            .addTo( this)

    }

    interface AdapterCallbacks {
        fun onPopularEventClickLister(popularEvent: Event)
    }
}