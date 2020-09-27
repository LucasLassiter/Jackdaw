package com.example.jackdaw

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.jackdaw.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_bottom_nav.*

class MainActivity : AppCompatActivity() {

    val retrieveAllSongs: RetrieveAllSongs = RetrieveAllSongs()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        retrieveAllSongs.retrieveAllSongs(this)

        Log.d("Songs", retrieveAllSongs.getAllSongsNames().size.toString())

        for(songs in retrieveAllSongs.getAllSongsNames())
        {
            Log.d("Songs", songs)
        }
    }
}