package com.lucasanimalfacts.jackdaw.domain.models

data class Album(
    val id: Int,
    val parent: Int,
    val title: String,
    val artist: String,
    val isDir: Boolean,
    val coverArt: Int
)
