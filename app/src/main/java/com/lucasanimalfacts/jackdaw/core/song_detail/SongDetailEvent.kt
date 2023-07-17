package com.lucasanimalfacts.jackdaw.core.song_detail

sealed class SongDetailEvent {
    data class starSong(val id: String): SongDetailEvent()
    data class playPause(val boolean: Boolean) : SongDetailEvent()
}
