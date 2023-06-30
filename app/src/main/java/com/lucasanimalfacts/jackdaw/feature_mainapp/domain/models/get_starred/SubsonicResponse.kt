package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred

data class SubsonicResponse(
    val serverVersion: String,
    val starred: Starred,
    val status: String,
    val type: String,
    val version: String
)