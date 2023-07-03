package com.lucasanimalfacts.jackdaw.core

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong

data class PlaylistDetailState(
    val name: String = "",
    val songList: List<StandardSong>? = null,
    val albumArtUrl: String? = null,
    val isAlbum: Boolean = false,
)
