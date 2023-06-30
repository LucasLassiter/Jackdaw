package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist

data class PlaylistX(
    val changed: String,
    val coverArt: String,
    val created: String,
    val duration: Int,
    val entry: List<Entry>,
    val id: String,
    val name: String,
    val owner: String,
    val `public`: Boolean,
    val songCount: Int
)