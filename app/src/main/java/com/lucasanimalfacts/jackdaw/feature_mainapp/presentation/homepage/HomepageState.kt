package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists.GetPlaylistsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder
import java.io.InputStream

data class HomepageState(
    val randomSongs: GetRandomSongsSubsonicResponseHolder? = null,
    val starred: GetStarredSubsonicResponseHolder? = null,
    val albumArt: InputStream? = null,
    val playlists: GetPlaylistsSubsonicResponseHolder? = null
)
