package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.GetAlbumSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.GetPlaylistSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists.GetPlaylistsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder

interface SubsonicRepository {
    suspend fun getRandomSongs(username: String, password: String, version: String, appName: String, responseType: String) : GetRandomSongsSubsonicResponseHolder
    suspend fun getStarred(username: String, password: String, version: String, appName: String, responseType: String) : GetStarredSubsonicResponseHolder
    suspend fun getCoverArt(username: String, password: String, version: String, appName: String, responseType: String, id: String, size: String) : AlbumArt
    suspend fun getPlaylists(username: String, password: String, version: String, appName: String, responseType: String) : GetPlaylistsSubsonicResponseHolder
    suspend fun getPlaylist(username: String, password: String, version: String, appName: String, responseType: String, id: String) : GetPlaylistSubsonicResponseHolder
    suspend fun getAlbum(username: String, password: String, version: String, appName: String, responseType: String, id: String) : GetAlbumSubsonicResponseHolder
}