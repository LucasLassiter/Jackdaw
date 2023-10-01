package com.lucasanimalfacts.jackdaw.core.playlist_detail

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasanimalfacts.jackdaw.core.service.MusicService
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.Entry
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.Song
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper,
    private val application: Application,
    ) : AndroidViewModel(application) {
    private val _sharedState = mutableStateOf(PlaylistDetailState())
    val sharedState: State<PlaylistDetailState> = _sharedState

    @SuppressLint("StaticFieldLeak")
    private lateinit var mService: MusicService
    private lateinit var song: StandardSong
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicPlayerBinder
            mService = binder.getService()
            mBound = true

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    init {
        Intent(this.application.applicationContext, MusicService::class.java).also { intent ->
            application.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    fun onEvent(event: PlaylistDetailEvent) {
        when (event) {
            is PlaylistDetailEvent.QueueSong -> {
                mService.addSong(event.song)
            }
            is PlaylistDetailEvent.LinearQueueAllSongs -> {
                Log.d("Adding Songs", "Attempting To Add")
                sharedState.value.songList?.forEach {
                    Log.d("Adding Songs", it.title)
                    mService.addSong(it)
                }
            }
        }
    }


    fun addAlbum(
        id: String,
        name: String
    ) {
        viewModelScope.launch {
            val album = useCaseWrapper.getAlbum(
                username = "Lucas",
                password = "ZPvl(<D-W6rj[Cb\"",
                version = "1.16.1",
                appName = "jackdaw",
                responseType = "json",
                id = id
            )
            val songList: List<Song> = album.`subsonic-response`.album.song
            val albumArtUrl = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=${album.`subsonic-response`.album.coverArt}"
            val isAlbum = true

            val standardSongList: MutableList<StandardSong> = emptyList<StandardSong>().toMutableList()
            songList.forEach {
                standardSongList.add(it.toStandardSong())
            }

            _sharedState.value = sharedState.value.copy(
                songList = standardSongList,
                albumArtUrl = albumArtUrl,
                isAlbum = isAlbum,
                name = name
            )
        }
    }

    fun addDataPlaylist(
        id: String,
        name: String
    ) {
        viewModelScope.launch {
            val playlist = useCaseWrapper.getPlaylist(
                username = "Lucas",
                password = "ZPvl(<D-W6rj[Cb\"",
                version = "1.16.1",
                appName = "jackdaw",
                responseType = "json",
                id = id
            )
            val songList: List<Entry> = playlist.`subsonic-response`.playlist.entry
            val albumArtUrl = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=${playlist.`subsonic-response`.playlist.coverArt}"
            val isAlbum = false

            val standardSongList: MutableList<StandardSong> = emptyList<StandardSong>().toMutableList()
            songList.forEach {
                standardSongList.add(it.toStandardSong())
            }

            _sharedState.value = sharedState.value.copy(
                songList = standardSongList,
                albumArtUrl = albumArtUrl,
                isAlbum = isAlbum,
                name = name
            )
        }
    }
}