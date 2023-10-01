package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.song_detail

data class SeekBarState(
    val formattedTime: String = "0:00",
    val unformattedTime: String = "0",
    val title: String = ""
)
