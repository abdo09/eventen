package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.sponsor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.databinding.FeaturedThisWeekItemBinding
import net.tinat.group.eventen.databinding.SponsorImageItemBinding
import net.tinat.group.eventen.databinding.SponsorItemListBinding

class SponsorAdapter: RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {

    inner class SponsorViewHolder(val sponsorItemListBinding: SponsorItemListBinding) :
        RecyclerView.ViewHolder(sponsorItemListBinding.root) {
        fun bind(sponsors: Event.Sponsors) {
            sponsorItemListBinding.sponsor = sponsors
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Event.Sponsors>() {
        override fun areItemsTheSame(oldItem: Event.Sponsors, newItem: Event.Sponsors): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event.Sponsors, newItem: Event.Sponsors): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        return SponsorViewHolder(
            SponsorItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Event.Sponsors) -> Unit)? = null

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)

        holder.sponsorItemListBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(event)
                }
            }
        }
    }

    fun setOnClickListener(listener: (Event.Sponsors) -> Unit) {
        onItemClickListener = listener
    }
}