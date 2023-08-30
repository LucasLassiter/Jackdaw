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
import com.lucasanimalfacts.jackdaw.R

@Composable
fun ButtonBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 38.dp, end = 38.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            FilledTonalButton(
                onClick = { /*TODO*/ },
//                modifier = Modifier.padding(end = 8.dp)
//                .size(50.dp),

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