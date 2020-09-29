package com.example.jackdaw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.playlist_recyclerview.view.*

class PlaylistAdapter(
    var playlists: List<Playlists>
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playlist_recyclerview, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.itemView.apply {
            playlist_name_textview.text = playlists[position].name
            total_songs_textview.text = playlists[position].totalSongs
        }
    }

    override fun getItemCount(): Int {
        return playlists.size
    }
}