package com.example.myhomework4.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myhomework4.databinding.ItemPlaylistBinding
import com.example.myhomework4.model.Playlists
import com.example.myhomework4.utils.loadImage

class PlayListAdapter(private val onClick: (Playlists.Item) -> Unit) : RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    private var list = ArrayList<Playlists.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlists.Item>) {
        this.list = list as ArrayList<Playlists.Item>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        return PlayListViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayListViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Playlists.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                tvNumberOfVideos.text = item.contentDetails?.itemCount.toString() + " video series"
                ivPlaylist.loadImage(item.snippet?.thumbnails?.default?.url!!)
                cvPlaylist.setOnClickListener {
                    onClick.invoke(item)
                }

            }

        }
    }
}