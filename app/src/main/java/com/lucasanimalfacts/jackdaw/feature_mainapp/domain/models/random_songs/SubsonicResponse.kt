package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs

data class SubsonicResponse(
    val randomSongs: RandomSongs,
    val serverVersion: String,
    val status: String,
    val type: String,
    val version: String
)