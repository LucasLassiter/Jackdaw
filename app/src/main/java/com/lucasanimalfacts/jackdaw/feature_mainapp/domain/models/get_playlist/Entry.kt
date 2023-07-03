package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong

data class Entry(
    val album: String,
    val albumId: String,
    val artist: String,
    val artistId: String,
    val bitRate: Int,
    val contentType: String,
    val coverArt: String,
    val created: String,
    val discNumber: Int,
    val duration: Int,
    val genre: String,
    val id: String,
    val isDir: Boolean,
    val isVideo: Boolean,
    val parent: String,
    val path: String,
    val playCount: Int,
    val played: String,
    val size: Int,
    val starred: String,
    val suffix: String,
    val title: String,
    val track: Int,
    val type: String,
    val userRating: Int,
    val year: Int
)

fun Entry.toStandardSong() = StandardSong(
    album = album,
    albumId = albumId,
    artist = artist,
    artistId = artistId,
    bitRate = bitRate,
    contentType = contentType,
    coverArt = coverArt,
    created = created,
    duration = duration,
    id = id,
    isDir = isDir,
    isVideo = isVideo,
    parent = parent,
    path = path,
    size = size,
    starred = "false",
    suffix = suffix,
    title = title,
    type = type,
    year = year
)

