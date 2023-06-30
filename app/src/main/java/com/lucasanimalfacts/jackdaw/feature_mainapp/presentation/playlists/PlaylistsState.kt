package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists.GetPlaylistsSubsonicResponseHolder

data class PlaylistsState(
    val playlists: GetPlaylistsSubsonicResponseHolder? = null
)
