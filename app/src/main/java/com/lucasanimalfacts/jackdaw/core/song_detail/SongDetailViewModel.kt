package com.lucasanimalfacts.jackdaw.core.song_detail

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasanimalfacts.jackdaw.core.service.MusicService
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper,
    private val application: Application,
) : AndroidViewModel(application) {
    private val _sharedState = mutableStateOf(SongDetailState())
    val sharedState: State<SongDetailState> = _sharedState

    @SuppressLint("StaticFieldLeak")
    private lateinit var mService: MusicService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("SongDetailViewModel", "inside")
            val binder = service as MusicService.LocalBinder
            mService = binder.getService()
            mBound = true

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    init {
            Log.d("SongDetailViewModel", "outside")

            Intent(this.application.applicationContext, MusicService::class.java).also { intent ->
                application.bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
    }

    fun onEvent(event: SongDetailEvent) {
        when (event) {
            is SongDetailEvent.starSong -> {
                if (_sharedState.value.starred)
                {
                    Log.d("StarModle", "UnStarred")
                    _sharedState.value = sharedState.value.copy(
                        starred = false
                    )
                    viewModelScope.launch {
                        _sharedState.value.song?.let {
                            useCaseWrapper.unStarSong(
                                username = "lucas",
                                password = "ZPvl(<D-W6rj[Cb\"",
                                version = "1.16.1",
                                appName = "jackdaw",
                                responseType = "json",
                                id = it.id
                            )
                        }
                    }
                } else {
                    Log.d("StarModle", "Starred")
                    _sharedState.value = sharedState.value.copy(
                        starred = true
                    )
                    viewModelScope.launch {
                        _sharedState.value.song?.let {
                            useCaseWrapper.starSong(
                                username = "lucas",
                                password = "ZPvl(<D-W6rj[Cb\"",
                                version = "1.16.1",
                                appName = "jackdaw",
                                responseType = "json",
                                id = it.id
                            )
                        }
                    }
                }
            }

            is SongDetailEvent.playPause -> {
                if (event.boolean) {
                    if (sharedState.value.started) {
                        Intent(this.application.applicationContext, MusicService::class.java).also {
                            it.action = MusicService.Actions.RESUME.toString()
                            application.startService(it)
                            _sharedState.value = sharedState.value.copy(
                                playing = true,
                                mediaPlayer = mService.mediaPlayer()
                            )
                        }
                    } else {

                        Log.d("SongDetailViewModel", mService.mediaPlayer().toString())

                        if (sharedState.value.playing) {
                            Intent(
                                this.application.applicationContext,
                                MusicService::class.java
                            ).also {

                                it.action = MusicService.Actions.STOP_PLAYER.toString()
                                application.startService(it)
                            }
                        }

                        Intent(this.application.applicationContext, MusicService::class.java).also {

                            it.putExtra(
                                MusicService.EXTRAS.ID.toString(),
                                sharedState.value.song?.id
                            )
                            it.putExtra(
                                MusicService.EXTRAS.TITLE.toString(),
                                sharedState.value.title
                            )
                            it.putExtra(
                                MusicService.EXTRAS.ARTIST.toString(),
                                sharedState.value.song!!.artist
                            )

                            it.action = MusicService.Actions.START.toString()
                            application.startService(it)
                        }

                        _sharedState.value = sharedState.value.copy(
                            started = true,
                            playing = true,
                            mediaPlayer = mService.mediaPlayer()
                        )
                    }
                } else {
                    Intent(this.application.applicationContext, MusicService::class.java).also {
                        it.action = MusicService.Actions.STOP.toString()
                        application.startService(it)
                    }
                    _sharedState.value = sharedState.value.copy(
                        playing = false,
                        mediaPlayer = mService.mediaPlayer()
                    )
                }
            }
            is SongDetailEvent.focusedScreen -> {
                _sharedState.value = sharedState.value.copy(
                    focused = event.boolean
                )
            }
        }
    }


    fun addSong(
        song: StandardSong
    ) {
        Log.d("addSong", song.starred.toBoolean().toString())
        Log.d("addSong", song.starred)
        _sharedState.value = sharedState.value.copy(
            title = song.title,
            song = song,
            albumArtUrl = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=${song.coverArt}",
            starred = song.starred != "false",
            started = song == sharedState.value.song,
            playing = sharedState.value.playing,
            mediaPlayer = mService.mediaPlayer()
        )

        onEvent(event = SongDetailEvent.playPause(true))
    }
}

