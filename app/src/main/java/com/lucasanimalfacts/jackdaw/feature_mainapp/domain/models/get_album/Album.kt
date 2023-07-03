package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album

data class Album(
    val artist: String,
    val artistId: String,
    val coverArt: String,
    val created: String,
    val duration: Int,
    val id: String,
    val name: String,
    val song: List<Song>,
    val songCount: Int,
    val starred: String,
    val year: Int
)