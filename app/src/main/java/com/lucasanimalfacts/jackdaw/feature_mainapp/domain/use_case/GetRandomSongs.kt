package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository

class GetRandomSongs(
    private val repository: SubsonicRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String
    ): GetRandomSongsSubsonicResponseHolder {
        return repository.getRandomSongs(username = username, password = password, version = version, appName = appName, responseType = responseType)
    }
}