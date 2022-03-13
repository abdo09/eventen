package net.tinat.group.eventen.ui.bottom_tabs.home.adapters.featuredThisWeek

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.EventDetailsViewModel
import net.tinat.group.eventen.utils.formatTime

@SuppressLint("SetTextI18n")
@BindingAdapter("handledLocation")
fun TextView.handledLocation(location: Event.Location?){
    location?.let {
        this.text = "${it.country}, ${it.city}"
    }
}

@BindingAdapter("handledEventStatus")
fun TextView.handledEventStatus(eventStatus: Event.EventStatus?){
    eventStatus?.let {
        this.text = it.name.replace("_", " ")
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("handledEventDate")
fun TextView.handledEventDate(date: Event.Date?){
    date?.let {
        this.text = "${it.dayOfTheWeek?.substring(0, 3)}\n${it.dayOfTheMonth}"
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("handledEventTime")
fun TextView.handledEventTime(time: Event.Time?){
    time?.let {
        this.text = formatTime(it
        )
    }
}

@BindingAdapter("eventDescriptionMaxLines")
fun TextView.eventDescriptionMaxLines(isExtended: Boolean?){
    isExtended?.let {
        if(it) this.maxLines = 10
        else this.maxLines = 3
    }
}

@BindingAdapter("eventDescriptionImageRotation")
fun ImageView.eventDescriptionImageRotation(isExtended: Boolean?){
    isExtended?.let {
        if(it) this.rotation = 180f
        else this.rotation = 360f
    }
}

