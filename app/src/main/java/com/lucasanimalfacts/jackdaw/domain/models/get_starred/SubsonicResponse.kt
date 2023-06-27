package com.lucasanimalfacts.jackdaw.domain.models.get_starred

data class SubsonicResponse(
    val serverVersion: String,
    val starred: Starred,
    val status: String,
    val type: String,
    val version: String
)