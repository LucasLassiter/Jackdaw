package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucasanimalfacts.jackdaw.core.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.HomepageViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.components.PlaylistComponent
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun Playlists(
    navController: NavController,
    sharedViewModel: PlaylistDetailViewModel,
    viewModel: PlaylistsViewModel = hiltViewModel()
) {
    LazyColumn(modifier = Modifier.fillMaxSize()
        .padding(16.dp)) {
        viewModel.state.value.playlists?.`subsonic-response`?.playlists?.playlist?.let {playlistsList ->
            items(playlistsList.size) {cur ->
                PlaylistComponent(modifier = Modifier
                    .padding(8.dp),
                    playlist = playlistsList[cur],
                    onClick = {
                        sharedViewModel.addDataPlaylist(playlistsList[cur].id, playlistsList[cur].name)
                        navController.navigate(Screen.PlaylistsDetail.route)
                    } )
            }
        }
    }
}