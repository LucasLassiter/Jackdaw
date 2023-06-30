package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository

class GetAlbumArt (
    private val repository: SubsonicRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String,
        id: String,
        size: String
    ): AlbumArt {
        return repository.getCoverArt(username = username, password = password, version = version, appName = appName, responseType = responseType, id = id, size = size)
    }
}