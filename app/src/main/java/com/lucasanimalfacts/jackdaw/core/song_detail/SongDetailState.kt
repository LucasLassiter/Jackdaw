package com.lucasanimalfacts.jackdaw.core.song_detail

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong

data class SongDetailState(
    val title: String = "",
    val song: StandardSong? = null,
    val albumArtUrl: String = "",
    val time: Int = 0,
    val starred: Boolean = false,
    val playing: Boolean = false,
    val started: Boolean = false,
)
