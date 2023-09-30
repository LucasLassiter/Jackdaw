package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.components.PlaylistComponent
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun Playlists(
    navController: NavController,
    sharedViewModel: PlaylistDetailViewModel,
    songSharedViewModel: SongDetailViewModel,
    viewModel: PlaylistsViewModel = hiltViewModel()
) {
    val navBackstack = remember { mutableStateOf(navController.visibleEntries.value.last().destination.route) }.also {
        it.value?.let { Log.d("navControllerDest", it) }
        if (it.value == Screen.SongDetail.route) {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(false))
        } else {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(true))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Playlists",
            fontSize = 32.sp,
            fontFamily = robotoBoldFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            viewModel.state.value.playlists?.`subsonic-response`?.playlists?.playlist?.let { playlistsList ->
                items(playlistsList.size) { cur ->
                    Box(modifier = Modifier.padding(bottom = 16.dp))
                    {
                        PlaylistComponent(modifier = Modifier
                            .padding(8.dp),
                            playlist = playlistsList[cur],
                            onClick = {
                                sharedViewModel.addDataPlaylist(
                                    playlistsList[cur].id,
                                    playlistsList[cur].name
                                )
                                navController.navigate(Screen.PlaylistsDetail.route)
                            })
                    }
                }
            }
        }
    }
}