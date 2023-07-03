package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.lucasanimalfacts.jackdaw.core.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.Album
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.RandomSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.StarredSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: PlaylistDetailViewModel,
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

                            }
                        }
                    }
                }
            }
        }
    }
    Log.d("MainActivity", viewModel.state.value.randomSongs?.`subsonic-response`.toString())
}