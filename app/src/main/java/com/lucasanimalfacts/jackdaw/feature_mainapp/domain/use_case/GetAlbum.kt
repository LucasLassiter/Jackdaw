package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.GetAlbumSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.GetPlaylistSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository

class GetAlbum (
    private val repository: SubsonicRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String,
        id: String,
    ): GetAlbumSubsonicResponseHolder {
        return repository.getAlbum(
            username = username,
            password = password,
            version = version,
            appName = appName,
            responseType = responseType,
            id = id
        )
    }
}