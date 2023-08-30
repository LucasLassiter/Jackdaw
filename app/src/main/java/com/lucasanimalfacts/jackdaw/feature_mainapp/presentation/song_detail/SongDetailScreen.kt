package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.song_detail

import android.content.ServiceConnection
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.shapes.Shape
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.SeekBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.R
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.service.MusicService
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailState
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.HomepageViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen
import java.io.IOException

@Composable
fun SongDetailScreen(
    navController: NavController,
    viewModel: SongDetailViewModel
) {

    val navBackstack = remember { mutableStateOf(navController.visibleEntries.value.last().destination.route) }.also {
        it.value?.let { Log.d("navControllerDest", it) }
        if (it.value == Screen.SongDetail.route) {
            viewModel.onEvent(SongDetailEvent.focusedScreen(false))
        }
    }
    if (viewModel.sharedState.value.song != null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 8.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Go Back", Modifier.size(55.dp))
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(Icons.Default.MoreVert, contentDescription = "Go Back", Modifier.size(25.dp))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 84.dp),
        ) {
            println(viewModel.sharedState.value.song!!.id)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = viewModel.sharedState.value.albumArtUrl,
                    contentDescription = "Cover art",
                    modifier = Modifier.height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 48.dp, top = 48.dp)
            ) {
                Column {
                    Text(
                        text = viewModel.sharedState.value.title,
                        fontSize = 20.sp,
                        fontFamily = robotoBoldFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(250.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = viewModel.sharedState.value.song!!.artist,
                        fontFamily = robotoBoldFamily,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.width(250.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                IconButton(onClick = {
                    viewModel.onEvent(SongDetailEvent.starSong(viewModel.sharedState.value.song!!.id))
                }) {
                    Icon(
                        if (viewModel.sharedState.value.starred) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Heart"
                    )
                }
            }
            Slider(
                value = ((viewModel.sharedState.value.mediaPlayer?.currentPosition?.toFloat() ?: 0f) / 1000000f),
                onValueChange = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = (viewModel.sharedState.value.mediaPlayer?.currentPosition).toString())
                Text(text = viewModel.sharedState.value.song!!.duration.toString())
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, top = 48.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_loop_24),
                        contentDescription = "Loop",
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Loop",
                        modifier = Modifier.size(40.dp)
                    )
                }
                FilledIconButton(
                    onClick = {
                        if (viewModel.sharedState.value.playing) {
                            Log.d("playingornah", "Pause")
                            viewModel.onEvent(SongDetailEvent.playPause(false))
                        } else {
                            Log.d("playingornah", "Play")
                            viewModel.onEvent(SongDetailEvent.playPause(true))
                        }
                    },
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        if (!viewModel.sharedState.value.playing) painterResource(R.drawable.baseline_play_arrow_24) else painterResource(
                            R.drawable.baseline_pause_24
                        ),
                        contentDescription = "Play / Pause",
                        modifier = Modifier.size(35.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Loop",
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_shuffle_24),
                        contentDescription = "Shuffle",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}