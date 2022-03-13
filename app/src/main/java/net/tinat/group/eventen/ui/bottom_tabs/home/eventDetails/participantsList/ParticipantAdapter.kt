package net.tinat.group.eventen.ui.bottom_tabs.home.eventDetails.participantsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.tinat.group.eventen.data.dto.Event
import net.tinat.group.eventen.data.dto.Participates
import net.tinat.group.eventen.databinding.ParticipantItemListBinding

class ParticipantAdapter: RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {

    inner class ParticipantViewHolder(val participantItemListBinding: ParticipantItemListBinding) :
        RecyclerView.ViewHolder(participantItemListBinding.root) {
        fun bind(participate: Participates) {
            participantItemListBinding.participant = participate
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        return ParticipantViewHolder(
            ParticipantItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Participates) -> Unit)? = null

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = differ.currentList[position]
        holder.bind(participant)

        holder.participantItemListBinding.apply {
            imageParticipant.setOnClickListener {
                onItemClickListener?.let {
                    it(participant)
                }
            }
            if (differ.currentList.size - 1 == position)
                this.bioAboutSeparator.visibility = View.INVISIBLE
        }
    }

    fun setOnClickListener(listener: (Participates) -> Unit) {
        onItemClickListener = listener
    }
}