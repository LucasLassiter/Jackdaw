package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

import android.content.ClipData.Item
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.toStandardSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.Album
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.RandomSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components.StarredSong
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.components.PlaylistComponent
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen
import com.lucasanimalfacts.jackdaw.navigation.SetupNavGraph

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: PlaylistDetailViewModel,
    songSharedViewModel: SongDetailViewModel,
    viewModel: HomepageViewModel = hiltViewModel()
) {
    val navBackstack = remember { mutableStateOf(navController.visibleEntries.value.last().destination.route) }.also {
        it.value?.let { Log.d("navControllerDest", it) }
        if (it.value == Screen.SongDetail.route) {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(false))
        } else {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(true))
        }
    }

    val contrast = 1f // 0f..10f (1 should be default)
    val brightness = -50f // -255f..255f (0 should be default)
    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Log.d("PlaylistDetailLog", "FirstThisHappened")

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search button",
                    Modifier.size(32.dp)
                )
                Text(
                    text = "Jackdaw",
                    fontSize = 32.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings button",
                    Modifier.size(32.dp)
                )
            }
            Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 0.dp),
                Arrangement.Center
            ) {
                AsyncImage(
                    model = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=" + (viewModel.state.value.starred?.`subsonic-response`?.starred?.album?.get(10)?.id
                        ?: ""),
                    contentDescription = "sdf",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                    modifier = Modifier
                        .width(350.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(24.dp)),
                )
            }

            Text(
                text = "Favorited Songs",
                fontSize = 32.sp,
                fontFamily = robotoBoldFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
            )

            LazyRow (
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
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

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, top = 24.dp, bottom = 8.dp, end = 16.dp),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Text(
                    text = "Playlists",
                    fontSize = 32.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "See More",
                    fontSize = 16.sp,
                    fontFamily = robotoBoldFamily,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Playlists.route)
                    }
                )
            }

        }

        viewModel.state.value.playlists?.`subsonic-response`?.playlists?.playlist?.let { playlistsList ->
            items(if (playlistsList.size > 5) 5 else playlistsList.size) { cur ->
                Box(modifier = Modifier.padding(bottom = 16.dp))
                {
                    PlaylistComponent(modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                        playlist = playlistsList[cur],
                        onClick = {
                            sharedViewModel.addDataPlaylist(
                                playlistsList[cur].id,
                                playlistsList[cur].name
                            )
                            navController.navigate(Screen.PlaylistsDetail.route)
                        }
                    )
                }
            }
        }
    }
    Log.d("MainActivity", viewModel.state.value.randomSongs?.`subsonic-response`.toString())
}