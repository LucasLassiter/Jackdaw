package com.lucasanimalfacts.jackdaw.core.playlist_detail

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.Song
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong

sealed class PlaylistDetailEvent {
    data class QueueSong(val song: StandardSong): PlaylistDetailEvent()
    data class LinearQueueAllSongs(val boolean: Boolean = true): PlaylistDetailEvent()
}