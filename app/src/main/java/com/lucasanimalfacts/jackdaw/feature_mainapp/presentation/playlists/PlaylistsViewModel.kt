package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper
) : ViewModel() {
    private val _state = mutableStateOf(PlaylistsState())
    val state: State<PlaylistsState> = _state

    init {
        viewModelScope.launch {
            getPlaylists()
        }
    }

    private suspend fun getPlaylists() {
        _state.value =  state.value.copy(
            playlists = useCaseWrapper.getPlaylists(username = "lucas", password = "ZPvl(<D-W6rj[Cb\"", version = "1.16.1", appName = "jackdaw", responseType = "json"),
        )
    }
}