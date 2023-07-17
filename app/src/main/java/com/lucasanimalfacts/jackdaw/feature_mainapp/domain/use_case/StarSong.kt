package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository

class StarSong(
    private val repository: SubsonicRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String,
        id: String,
    ) {
        repository.star(
            username = username,
            password = password,
            version = version,
            appName = appName,
            responseType = responseType,
            id = id
        )
    }
}