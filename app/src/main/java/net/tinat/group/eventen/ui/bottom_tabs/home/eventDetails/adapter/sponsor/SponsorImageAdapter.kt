package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.sponsor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.data.dto.Sponsors
import net.tinat.group.eventen.databinding.FeaturedThisWeekItemBinding
import net.tinat.group.eventen.databinding.SponsorImageItemBinding

class SponsorImageAdapter: RecyclerView.Adapter<SponsorImageAdapter.SponsorImageViewHolder>() {

    inner class SponsorImageViewHolder(val sponsorImageItemBinding: SponsorImageItemBinding) :
        RecyclerView.ViewHolder(sponsorImageItemBinding.root) {
        fun bind(sponsors: Sponsors) {
            sponsorImageItemBinding.sponsor = sponsors
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Sponsors>() {
        override fun areItemsTheSame(oldItem: Sponsors, newItem: Sponsors): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sponsors, newItem: Sponsors): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorImageViewHolder {
        return SponsorImageViewHolder(
            SponsorImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Sponsors) -> Unit)? = null

    override fun onBindViewHolder(holder: SponsorImageViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)

        holder.sponsorImageItemBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(event)
                }
            }
        }
    }

    fun setOnClickListener(listener: (Sponsors) -> Unit) {
        onItemClickListener = listener
    }
}