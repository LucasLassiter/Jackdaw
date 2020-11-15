package com.example.jackdaw

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.jackdaw.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val retrieveAllSongs: RetrieveAllSongs = RetrieveAllSongs()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        var pagerAdapter = PagerAdapter(supportFragmentManager, binding.tabBar.tabCount)
        binding.viewPager.adapter = pagerAdapter
        binding.tabBar.setupWithViewPager(binding.viewPager)

        //TODO:: Find a way to do this with xml or something cause I don't think it should
        // be done with code
        binding.tabBar.getTabAt(0)?.setIcon(R.drawable.ic_baseline_playlist_play_36)
        binding.tabBar.getTabAt(1)?.setIcon(R.drawable.ic_baseline_library_music_36)
        binding.tabBar.getTabAt(2)?.setIcon(R.drawable.ic_baseline_search_36)
        binding.viewPager.currentItem = 1
    }
}