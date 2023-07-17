package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.Album
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.RandomSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.StarredSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: PlaylistDetailViewModel,
    songSharedViewModel: SongDetailViewModel,
    viewModel: HomepageViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Starred Albums",
                    fontSize = 32.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Bold
                )

                LazyRow {
                    viewModel.state.value.starred?.`subsonic-response`?.starred?.album?.let { albumList ->
                        items(albumList.size) { cur ->
                            Album(modifier = Modifier,
                                album = albumList[cur],
                                onClick = {
                                    Log.d("Homescreen", albumList[cur].id)
                                    sharedViewModel.addAlbum(albumList[cur].id, albumList[cur].name)
                                    navController.navigate(Screen.PlaylistsDetail.route)
                            })
                        }
                    }
                }

                Text(
                    text = "Starred Songs",
                    fontSize = 32.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Bold
                )

                LazyRow {
                    viewModel.state.value.starred?.`subsonic-response`?.starred?.song?.let { songList ->
                        items(songList.size) { cur ->
                            StarredSong(modifier = Modifier, song = songList[cur]) {
                                songSharedViewModel.addSong(songList[cur].toStandardSong())
                                navController.navigate(Screen.SongDetail.route)
                            }
                        }
                    }
                }

                Text(
                    text = "Random",
                    fontSize = 32.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Bold
                )

                LazyRow {
                    viewModel.state.value.randomSongs?.`subsonic-response`?.randomSongs?.song?.let { songList ->
                        items(songList.size) { cur ->
                            RandomSong(modifier = Modifier, song = songList[cur]) {
                                songSharedViewModel.addSong(songList[cur].toStandardSong())
                                navController.navigate(Screen.SongDetail.route)
                            }
                        }
                    }
                }
            }
        }
    }
    Log.d("MainActivity", viewModel.state.value.randomSongs?.`subsonic-response`.toString())
}