package com.lucasanimalfacts.jackdaw.domain.models.get_starred

data class Starred(
    val album: List<Album>,
    val song: List<Song>
)