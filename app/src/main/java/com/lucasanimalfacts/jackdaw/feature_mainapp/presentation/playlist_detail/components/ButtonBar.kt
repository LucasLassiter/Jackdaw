package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lucasanimalfacts.jackdaw.R
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailEvent
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun ButtonBar(
    modifier: Modifier = Modifier,
    viewModel: PlaylistDetailViewModel,
    sharedViewModel: SongDetailViewModel,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 38.dp, end = 38.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            FilledTonalButton(
                onClick =
                {
                    viewModel.onEvent(PlaylistDetailEvent.LinearQueueAllSongs())
                    if (viewModel.sharedState.value.songList?.get(0) != null) {
                        sharedViewModel.addSong(viewModel.sharedState.value.songList!![0])
                    }
                    navController.navigate(Screen.SongDetail.route)
                },

            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    modifier = Modifier.size(32.dp)
                )
                Text("Play")
            }
            
            Spacer(modifier = Modifier.padding(4.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
//                modifier = Modifier.size(50.dp),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_shuffle_24),
                    contentDescription = "Shuffle",
                    modifier = Modifier.size(32.dp)
                )
                Text("Shuffle")
            }
        }
        Column {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Menu,
                contentDescription = "menu"
                )
            }
        }
    }
}