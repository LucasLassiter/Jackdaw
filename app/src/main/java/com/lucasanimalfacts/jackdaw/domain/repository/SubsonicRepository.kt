package com.lucasanimalfacts.jackdaw.domain.repository

import com.lucasanimalfacts.jackdaw.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder

interface SubsonicRepository {
    suspend fun getRandomSongs(username: String, password: String, version: String, appName: String, responseType: String) : GetRandomSongsSubsonicResponseHolder
    suspend fun getStarred(username: String, password: String, version: String, appName: String, responseType: String) : GetStarredSubsonicResponseHolder
    suspend fun getCoverArt(username: String, password: String, version: String, appName: String, responseType: String, id: String) : AlbumArt
}