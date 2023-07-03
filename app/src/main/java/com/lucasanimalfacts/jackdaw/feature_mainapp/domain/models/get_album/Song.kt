package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong

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

fun Song.toStandardSong() = StandardSong(
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