package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists

data class SubsonicResponse(
    val playlists: Playlists,
    val serverVersion: String,
    val status: String,
    val type: String,
    val version: String
)