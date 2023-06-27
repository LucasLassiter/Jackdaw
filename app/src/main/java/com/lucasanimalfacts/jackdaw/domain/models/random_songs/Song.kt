package com.lucasanimalfacts.jackdaw.domain.models.random_songs

data class Song(
    val album: String,
    val albumId: String,
    val artist: String,
    val artistId: String,
    val bitRate: Int,
    val contentType: String,
    val coverArt: String,
    val created: String,
    val duration: Int,
    val id: String,
    val isDir: Boolean,
    val isVideo: Boolean,
    val parent: String,
    val path: String,
    val size: Int,
    val suffix: String,
    val title: String,
    val type: String,
    val year: Int
)