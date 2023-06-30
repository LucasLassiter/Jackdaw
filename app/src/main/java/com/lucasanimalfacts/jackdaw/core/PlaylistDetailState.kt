package com.lucasanimalfacts.jackdaw.core

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.Entry

data class PlaylistDetailState(
    val name: String = "",
    val songList: List<Entry>? = null,
    val albumArtUrl: String? = null,
    val isAlbum: Boolean = false
)
