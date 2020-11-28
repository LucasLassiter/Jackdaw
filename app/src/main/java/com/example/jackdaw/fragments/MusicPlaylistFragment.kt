package com.example.jackdaw.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jackdaw.adapters.SongAdapter
import com.example.jackdaw.R
import com.example.jackdaw.helpers.RetrieveAllSongs
import com.example.jackdaw.dataClasses.SongsDataClass
import com.example.jackdaw.databinding.FragmentMusicPlaylistBinding


class MusicPlaylistFragment : Fragment() {

    private var retrieveAllSongs: RetrieveAllSongs = RetrieveAllSongs()

    @RequiresApi(Build.VERSION_CODES.Q)
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


        context?.let { retrieveAllSongs.retrieveAllSongs(it) }

        for(name in retrieveAllSongs.getAllSongsNames())
        {
            musicList.add(SongsDataClass(name, 4))
        }


        val adapter = SongAdapter(musicList)
        binding.musiclistRecycleview.adapter = adapter
        binding.musiclistRecycleview.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}