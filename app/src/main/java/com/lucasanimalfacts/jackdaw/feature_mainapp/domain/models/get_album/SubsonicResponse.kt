package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album

data class SubsonicResponse(
    val album: Album,
    val serverVersion: String,
    val status: String,
    val type: String,
    val version: String
)