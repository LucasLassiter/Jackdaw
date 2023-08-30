package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.Song


@OptIn(ExperimentalCoilApi::class)
@Composable
fun RandomSong(
    modifier: Modifier,
    song: Song,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .clickable { onClick() }
            .padding(4.dp)
            .width(150.dp)
    ) {
        AsyncImage(
            model = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=" + song.coverArt,
            contentDescription = "sdf",
            modifier = Modifier.height(150.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = song.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = song.artist, color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}