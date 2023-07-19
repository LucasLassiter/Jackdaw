package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.components.ButtonBar
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.components.SongBar
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun PlaylistDetailScreen(
    sharedViewModel: PlaylistDetailViewModel,
    songSharedViewModel: SongDetailViewModel,
    navController: NavController
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
        if (!sharedViewModel.sharedState.value.songList.isNullOrEmpty()) {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = sharedViewModel.sharedState.value.name,
                            fontFamily = robotoBoldFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        if (sharedViewModel.sharedState.value.isAlbum) {
                            AsyncImage(
                                model = sharedViewModel.sharedState.value.albumArtUrl,
                                contentDescription = "album art",
                                modifier = Modifier.height(300.dp)
                            )
                        } else {
                            Log.d(
                                "detailscreen",
                                sharedViewModel.sharedState.value.albumArtUrl.toString()
                            )
                            AsyncImage(
                                model = sharedViewModel.sharedState.value.albumArtUrl,
                                contentDescription = "playlist art",
                                modifier = Modifier.height(300.dp)
                            )
                        }
                    }
                }
                item {
                    ButtonBar()
                }

                items(sharedViewModel.sharedState.value.songList!!.size) {
                    SongBar(
                        entry = sharedViewModel.sharedState.value.songList!![it],
                        isAlbum = sharedViewModel.sharedState.value.isAlbum,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, 8.dp),
                            onClick = {
                                songSharedViewModel.addSong(sharedViewModel.sharedState.value.songList!![it])
                                navController.navigate(Screen.SongDetail.route)
                            }
                    )
                }
                item {
                    Divider(
                        thickness = 8.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}