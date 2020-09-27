package com.example.jackdaw

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.jackdaw.databinding.FragmentBottomNavBinding
import com.example.jackdaw.databinding.FragmentMusicBinding
import kotlinx.android.synthetic.main.fragment_bottom_nav.*

/**
 * A simple [Fragment] subclass.
 * Use the [BottomNavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class BottomNavFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentBottomNavBinding>(inflater, R.layout.fragment_bottom_nav, container, false)

        binding.playlistButton.setOnClickListener { view: View ->
            Log.d("Error", "0")
            Navigation.findNavController(view).navigate(R.id.action_playListFragment_to_musicFragment)
        }

        return binding.root
    }




}