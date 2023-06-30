package com.lucasanimalfacts.jackdaw.feature_mainapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lucasanimalfacts.jackdaw.feature_mainapp.data.repository.SubsonicRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubsonicViewModel(application: Application): AndroidViewModel(application) {

    private val repository: SubsonicRepositoryImpl = SubsonicRepositoryImpl()

    fun getRandomSongs() {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getRandomSongs(username = "lucas", password = "ZPvl(<D-W6rj[Cb\"", version = "1.16.1", appName = "jackdaw", responseType = "json")
        }
    }

}