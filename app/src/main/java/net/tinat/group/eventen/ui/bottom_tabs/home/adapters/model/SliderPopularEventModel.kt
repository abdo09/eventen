/*
 * Copyright (c) 2018. this code belongs to Z3R0 any modifications is prohibited
 */

package net.tinat.group.eventen.ui.bottom_tabs.home.adapters.model

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import net.tinat.group.eventen.R
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.utils.formatTime
import net.tinat.group.eventen.utils.loadWithGlide

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.popular_event_item)
abstract class SliderPopularEventModel : EpoxyModelWithHolder<SliderPopularEventModel.Holder>() {
    @EpoxyAttribute(hash = false)
    lateinit var popularEvent: Event

    @EpoxyAttribute(hash = false)
    var onClickedItemListener: View.OnClickListener? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        popularEvent.let {

            holder.eventItem.setOnClickListener(onClickedItemListener)

            holder.tvPopularDate.text = "${popularEvent.date?.dayOfTheWeek?.substring(0, 3)}\n${popularEvent.date?.dayOfTheMonth}"
            holder.tvPopularStatus.text = popularEvent.status?.name?.replace("_", " ")

            holder.popularEventImage.context.apply {
                loadWithGlide(holder.popularEventImage, R.drawable.popular_event)
            }

        }
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        holder.eventItem.setOnClickListener(null)
    }

    class Holder : EpoxyHolder() {
        lateinit var eventItem: CardView
        lateinit var popularEventImage: ImageView
        lateinit var tvPopularStatus: TextView
        lateinit var tvPopularDate: TextView
        override fun bindView(itemView: View) {
            eventItem = itemView.findViewById(R.id.popular_event_card_view)
            popularEventImage = itemView.findViewById(R.id.popular_event_image)
            tvPopularDate = itemView.findViewById(R.id.tv_popular_date)
            tvPopularStatus = itemView.findViewById(R.id.popular_status)
        }
    }
}