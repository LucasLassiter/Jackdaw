package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistDetailScreen(
    sharedViewModel: PlaylistDetailViewModel,
    songSharedViewModel: SongDetailViewModel,
    navController: NavController
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    val navBackstack = remember { mutableStateOf(navController.visibleEntries.value.last().destination.route) }.also {
        it.value?.let { Log.d("navControllerDest", it) }
        if (it.value == Screen.SongDetail.route) {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(false))
        } else {
            songSharedViewModel.onEvent(SongDetailEvent.focusedScreen(true))
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = sharedViewModel.sharedState.value.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = robotoBoldFamily
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
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
                                    modifier = Modifier
                                        .height(300.dp)
                                        .clip(RoundedCornerShape(4))
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
}