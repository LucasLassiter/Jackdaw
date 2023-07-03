package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules

data class StandardAlbum(
    val album: String,
    val artist: String,
    val artistId: String,
    val coverArt: String,
    val created: String,
    val duration: Int,
    val genre: String,
    val id: String,
    val isDir: Boolean,
    val isVideo: Boolean,
    val name: String,
    val parent: String,
    val playCount: Int,
    val played: String,
    val songCount: Int,
    val starred: String,
    val title: String,
    val userRating: Int,
    val year: Int
)
