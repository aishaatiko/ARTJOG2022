package com.alicea.artjog2022

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CreationGridAdapter(private val listCreation: ArrayList<Creation>): RecyclerView.Adapter<CreationGridAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: CreationGridAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: CreationGridAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_creation_title)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_creation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_gallery, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val creation = listCreation[position]

        Glide.with(holder.itemView.context)
            .load(creation.photo)
            .into(holder.imgPhoto)

        holder.tvTitle.text = creation.title

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCreation[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int {
        return listCreation.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Creation, position: Int)
    }
}