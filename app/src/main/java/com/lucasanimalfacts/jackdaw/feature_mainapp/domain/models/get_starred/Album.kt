package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardAlbum

data class Album(
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

fun Album.toStandardAlbum() = StandardAlbum(
    album = album,
    artist = artist,
    artistId = artistId,
    coverArt = coverArt,
    created = created,
    duration = duration,
    genre = genre,
    id = id,
    isDir = isDir,
    isVideo = isVideo,
    name = name,
    parent = parent,
    playCount = playCount,
    played = played,
    songCount = songCount,
    starred = starred,
    title = title,
    userRating = userRating,
    year = year
)