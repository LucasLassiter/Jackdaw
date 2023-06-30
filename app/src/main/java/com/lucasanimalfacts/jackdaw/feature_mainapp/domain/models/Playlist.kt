package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models

data class Playlist(
    val id: Int,
    val name: String,
    val comment: String,
    val owner: String,
    val public: Boolean,
    val songCount: Int,
    val duration: Int,
    val created: String,
    val coverArt: String
)
