package com.example.jackdaw

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jackdaw.databinding.FragmentMusicBinding
import com.example.jackdaw.databinding.FragmentPlayListBinding
import kotlinx.android.synthetic.main.fragment_play_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("Error", "1")

        val binding = DataBindingUtil.inflate<FragmentPlayListBinding>(
            inflater,
            R.layout.fragment_play_list,
            container,
            false
        )

        var playlistsList = mutableListOf(
            Playlists("All songs", "64 Songs"),
            Playlists("Better Songs", "43 Songs")
        )

        val adapter = PlaylistAdapter(playlistsList)
        binding.playlistsRecyclerview.adapter = adapter
        binding.playlistsRecyclerview.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}