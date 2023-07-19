package com.lucasanimalfacts.jackdaw.core.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.lucasanimalfacts.jackdaw.R

class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private var mMediaPlayer: MediaPlayer? = null
    private var title: String = ""
    private var artist: String = ""


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start(intent = intent, id = intent.extras?.getString(EXTRAS.ID.toString())!!)
            Actions.STOP.toString() -> stop()
            Actions.RESUME.toString() -> resume()
            Actions.STOP_PLAYER.toString() -> stopPlayer()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(intent: Intent?, id: String) {
        title = intent!!.getStringExtra(EXTRAS.TITLE.toString()).toString()
        artist = intent.getStringExtra(EXTRAS.ARTIST.toString()).toString()
        val notification = NotificationCompat.Builder(this, "music_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(artist)
            .build()
        startForeground(1, notification)



        mMediaPlayer = MediaPlayer().apply {
            setOnPreparedListener(this@MusicService)
            setDataSource("http://lucasanimalfacts.com:4533/rest/stream?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=jackdaw&f=json&id=${id}")

            prepareAsync()
        }
    }

    private fun resume() {
        mMediaPlayer?.start()
    }

    private fun stop() {
        mMediaPlayer?.pause()
    }

    private fun stopPlayer() {
        mMediaPlayer?.stop()
    }

    enum class Actions {
        START, STOP, RESUME, STOP_PLAYER
    }

    enum class EXTRAS {
        ID, TITLE, ARTIST
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }
}