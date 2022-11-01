package com.alicea.artjog2022

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ParticipantListAdapter(private val listParticipant: ArrayList<Participant>): RecyclerView.Adapter<ParticipantListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name_participant)
        var tvCreation: TextView = itemView.findViewById(R.id.tv_creation_participant)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_photo_participant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_participant, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val participant = listParticipant[position]

        Glide.with(holder.itemView.context)
            .load(participant.photo)
            .into(holder.imgPhoto)

        holder.tvName.text = participant.name
        holder.tvCreation.text = participant.creation

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listParticipant[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int {
        return listParticipant.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Participant, position: Int)
    }
}