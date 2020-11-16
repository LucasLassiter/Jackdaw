package com.example.jackdaw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.playlist_recyclerview.view.*
import kotlinx.android.synthetic.main.song_recycleview.view.*

class SongAdapter(
    var song: List<SongsDataClass>
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_recycleview, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.itemView.apply {
            songname_textview.text = song[position].name
            songlength_textview.text = song[position].duration.toString()
        }
    }

    override fun getItemCount(): Int {
        return song.size
    }
}