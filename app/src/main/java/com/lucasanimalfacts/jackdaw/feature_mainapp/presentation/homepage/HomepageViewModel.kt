package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper
) : ViewModel() {
    private val _state = mutableStateOf(HomepageState())
    val state: State<HomepageState> = _state

    init {
        viewModelScope.launch {
            getRandomSongs()
        }
    }

    fun onEvent(event: HomepageEvent) {
        when (event) {
            is HomepageEvent.GetAlbumArt -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        albumArt = useCaseWrapper.getAlbumArt(
                            username = "lucas",
                            password = "ZPvl(<D-W6rj[Cb\"",
                            version = "1.16.1",
                            appName = "jackdaw",
                            responseType = "json",
                            id = event.id,
                            size = "10"
                        ).binaryStream
                    )
                }
            }
        }
    }

    private suspend fun getRandomSongs() {
        _state.value =  state.value.copy(
            randomSongs = useCaseWrapper.getRandomSongs(username = "lucas", password = "ZPvl(<D-W6rj[Cb\"", version = "1.16.1", appName = "jackdaw", responseType = "json"),
            starred = useCaseWrapper.getStarred(username = "lucas", password = "ZPvl(<D-W6rj[Cb\"", version = "1.16.1", appName = "jackdaw", responseType = "json")
        )
    }
}