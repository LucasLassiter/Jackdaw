package com.example.jackdaw

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jackdaw.databinding.FragmentMusicBinding
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [MusicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MusicFragment : Fragment() {
    var player: MediaPlayer? = null

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
        
        return binding.root
    }

    private fun playPauseButton(view: View, binding: FragmentMusicBinding)
    {
        if (player == null)
        {
            player = MediaPlayer.create(context, R.raw.sales_renee)

            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(player!!.duration.toLong())
            val seconds = TimeUnit.MILLISECONDS.toSeconds(player!!.duration.toLong()) % 60

            if (minutes < 9)
            {
                binding.songDurationTextView.text = "0" + minutes.toString() + ":" + seconds.toString()
            }
            else
            {
                binding.songDurationTextView.text = minutes.toString() + ":" + seconds.toString()
            }


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
}