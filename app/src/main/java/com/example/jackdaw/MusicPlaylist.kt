package com.example.jackdaw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jackdaw.databinding.FragmentMusicBinding
import com.example.jackdaw.databinding.FragmentMusicPlaylistBinding


class MusicPlaylist : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMusicPlaylistBinding>(
            inflater,
            R.layout.fragment_music_playlist,
            container,
            false
        )

        var musicList = mutableListOf(
            SongsDataClass("Dramamine", 4)
        )

        val adapter = SongAdapter(musicList)
        binding.musiclistRecycleview.adapter = adapter
        binding.musiclistRecycleview.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}