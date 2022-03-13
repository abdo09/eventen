package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.databinding.FeaturedThisWeekItemBinding

class SponsorImageAdapter: RecyclerView.Adapter<SponsorImageAdapter.EventViewHolder>() {

    inner class EventViewHolder(val featuredThisWeekItemBinding: FeaturedThisWeekItemBinding) :
        RecyclerView.ViewHolder(featuredThisWeekItemBinding.root) {
        fun bind(event: Event) {
            featuredThisWeekItemBinding.event = event
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            FeaturedThisWeekItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Event) -> Unit)? = null

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)

        holder.featuredThisWeekItemBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(event)
                }
            }
        }
    }

    fun setOnClickListener(listener: (Event) -> Unit) {
        onItemClickListener = listener
    }
}