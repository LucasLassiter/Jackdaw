package com.example.jackdaw.fragments

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jackdaw.R
import com.example.jackdaw.helpers.RetrieveAllSongs
import com.example.jackdaw.databinding.FragmentMusicBinding
import com.example.jackdaw.services.MusicForegroundService
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [MusicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MusicFragment : Fragment() {
    private var player: MediaPlayer? = null
    private var retrieveAllSongs: RetrieveAllSongs = RetrieveAllSongs()
    private var currentSongIndex = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMusicBinding>(
            inflater,
            R.layout.fragment_music,
            container,
            false
        )

        binding.playPauseImageButton.setOnClickListener { playPauseButton(
            binding.playPauseImageButton,
            binding
        ) }
        player?.duration

        context?.let { retrieveAllSongs.retrieveAllSongs(it) }
        
        return binding.root
    }

    private fun playPauseButton(view: View, binding: FragmentMusicBinding)
    {

        startForeground
        if (player == null)
        {
            currentSongIndex = 6
            // Create MediaPlayer
            player = MediaPlayer.create(context, retrieveAllSongs.getAllSongsURI()[currentSongIndex])

            // Update UI song info
            updateMusicInfo(binding)

            binding.playPauseImageButton.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
            player!!.start()
        }
        else
        {
            if (player!!.isPlaying)
            {
                player!!.pause()
                view.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
            else
            {
                view.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
                player!!.start()
            }
        }
    }

    private fun updateMusicInfo(binding: FragmentMusicBinding){
        binding.musicNameTextView.text = retrieveAllSongs.getAllSongsNames()[currentSongIndex]

        val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(player!!.duration.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(player!!.duration.toLong()) % 60 

        var tempTime = if (minutes < 9) {
            "0" + minutes.toString() + ":"
        } else {
            minutes.toString() + ":"
        }

        tempTime = if (seconds < 9) {
            tempTime + "0" + seconds.toString()
        } else {
            tempTime + seconds.toString()
        }
        binding.songDurationTextView.text = tempTime

    }
}