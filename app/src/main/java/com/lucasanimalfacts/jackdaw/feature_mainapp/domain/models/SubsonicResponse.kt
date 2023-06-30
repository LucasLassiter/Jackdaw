package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.PlaylistX

data class SubsonicResponse(
    val playlist: PlaylistX,
    val serverVersion: String,
    val status: String,
    val type: String,
    val version: String
)