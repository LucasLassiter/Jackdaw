package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists

data class Playlist(
    val changed: String,
    val coverArt: String,
    val created: String,
    val duration: Int,
    val id: String,
    val name: String,
    val owner: String,
    val `public`: Boolean,
    val songCount: Int
)