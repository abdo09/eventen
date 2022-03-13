package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.adapter.participant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.data.dto.Participates
import net.tinat.group.eventen.databinding.ParticipantImageItemBinding

class ParticipantImageAdapter: RecyclerView.Adapter<ParticipantImageAdapter.ParticipantImageViewHolder>() {

    inner class ParticipantImageViewHolder(val participantImageItemBinding: ParticipantImageItemBinding) :
        RecyclerView.ViewHolder(participantImageItemBinding.root) {
        fun bind(participate: Participates) {
            participantImageItemBinding.participant = participate
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Participates>() {
        override fun areItemsTheSame(oldItem: Participates, newItem: Participates): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Participates, newItem: Participates): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantImageViewHolder {
        return ParticipantImageViewHolder(
            ParticipantImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Participates) -> Unit)? = null

    override fun onBindViewHolder(holder: ParticipantImageViewHolder, position: Int) {
        val participant = differ.currentList[position]
        holder.bind(participant)

        holder.participantImageItemBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(participant)
                }
            }
        }
    }

    fun setOnClickListener(listener: (Participates) -> Unit) {
        onItemClickListener = listener
    }
}