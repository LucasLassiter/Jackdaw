package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists.GetPlaylistsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository

class GetPlaylists (
    private val repository: SubsonicRepository
    ) {
        suspend operator fun invoke(
            username: String,
            password: String,
            version: String,
            appName: String,
            responseType: String,
        ): GetPlaylistsSubsonicResponseHolder {
            return repository.getPlaylists(username = username, password = password, version = version, appName = appName, responseType = responseType)
        }
    }