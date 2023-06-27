package com.lucasanimalfacts.jackdaw.domain.use_case

import com.lucasanimalfacts.jackdaw.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.domain.repository.SubsonicRepository

class GetStarred(
    private val repository: SubsonicRepository
    ) {
        suspend operator fun invoke(
            username: String,
            password: String,
            version: String,
            appName: String,
            responseType: String
        ): GetStarredSubsonicResponseHolder {
            return repository.getStarred(username = username, password = password, version = version, appName = appName, responseType = responseType)
        }
    }