package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.participant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.databinding.ParticipantImageItemBinding
import net.tinat.group.eventen.databinding.ParticipantItemListBinding

class ParticipantAdapter: RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {

    inner class ParticipantViewHolder(val participantItemListBinding: ParticipantItemListBinding) :
        RecyclerView.ViewHolder(participantItemListBinding.root) {
        fun bind(participate: Event.Participates) {
            participantItemListBinding.participant = participate
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Event.Participates>() {
        override fun areItemsTheSame(oldItem: Event.Participates, newItem: Event.Participates): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event.Participates, newItem: Event.Participates): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        return ParticipantViewHolder(
            ParticipantItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Event.Participates) -> Unit)? = null

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = differ.currentList[position]
        holder.bind(participant)

        holder.participantItemListBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(participant)
                }
            }
        }
    }

    fun setOnClickListener(listener: (Event.Participates) -> Unit) {
        onItemClickListener = listener
    }
}