package com.lucasanimalfacts.jackdaw.presentation.homepage

import com.lucasanimalfacts.jackdaw.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder
import java.io.InputStream

data class HomepageState(
    val randomSongs: GetRandomSongsSubsonicResponseHolder? = null,
    val starred: GetStarredSubsonicResponseHolder? = null,
    val albumArt: InputStream? = null
)
