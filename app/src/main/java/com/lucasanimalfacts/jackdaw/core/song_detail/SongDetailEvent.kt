package com.lucasanimalfacts.jackdaw.core.song_detail

sealed class SongDetailEvent {
    data class starSong(val id: String): SongDetailEvent()
    data class playPause(val boolean: Boolean): SongDetailEvent()
    data class focusedScreen(val boolean: Boolean): SongDetailEvent()
    data class skipSong(val boolean: Boolean = true): SongDetailEvent()
}
