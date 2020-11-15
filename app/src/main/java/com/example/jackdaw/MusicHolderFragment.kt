package com.example.jackdaw

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jackdaw.databinding.ActivityMainBinding
import com.example.jackdaw.databinding.FragmentMusicBinding
import com.example.jackdaw.databinding.FragmentMusicHolderBinding
import com.example.jackdaw.databinding.FragmentPlayListBinding

private const val NUM_PAGES = 2

class MusicHolderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentMusicHolderBinding>(
            inflater,
            R.layout.fragment_music_holder,
            container,
            false
        )

        val pagerAdapter = ScreenSlidePagerAdapter(this.requireActivity())
        binding.viewPager.adapter = pagerAdapter

        return binding.root
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> MusicFragment()
                1 -> MusicPlaylist()
                else -> null!!
            }
        }
    }
}