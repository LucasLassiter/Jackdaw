package com.lucasanimalfacts.jackdaw.core.service

import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.service.media.MediaBrowserService
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.lucasanimalfacts.jackdaw.R
import com.lucasanimalfacts.jackdaw.core.service.callbacks.MusicPlayerNotificationListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.inject.Inject

private const val SERVICE_TAG = "MusicService"

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat(), MediaPlayer.OnPreparedListener {

    @Inject
    lateinit var exoPlayer: SimpleExoPlayer

    private lateinit var musicNotificationManager: MusicNotificationManager

    private var mMediaPlayer: MediaPlayer? = null
    private var title: String = ""
    private var artist: String = ""
    private val binder = MusicPlayerBinder()
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector

    var isForegroundService = false

    override fun onCreate() {
        super.onCreate()
        val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
            PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_MUTABLE)
        }

        mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
            setSessionActivity(activityIntent)
            isActive = true
        }

        sessionToken = mediaSession.sessionToken

        musicNotificationManager = MusicNotificationManager(
            this,
            mediaSession.sessionToken,
            MusicPlayerNotificationListener(this)
        ) {

        }

        mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setPlayer(exoPlayer)
    }

    inner class MusicPlayerBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("SeekBarViewModel", "hi")
        return binder
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        TODO("Not yet implemented")
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        TODO("Not yet implemented")
    }

    fun mediaPlayer(): MediaPlayer? {
        return mMediaPlayer
    }

    inner class LocalBinder : Binder() {
        fun getService(): MusicService = this@MusicService
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

    fun getTime(): Int {
        if (mMediaPlayer != null) {
            return mMediaPlayer!!.currentPosition
        }
        return -1
    }

}