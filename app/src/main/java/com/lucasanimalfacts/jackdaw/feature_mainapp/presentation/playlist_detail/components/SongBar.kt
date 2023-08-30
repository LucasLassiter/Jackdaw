package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.standard_modules.StandardSong
import kotlin.math.roundToInt


enum class DragValue { Start, Center, End }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongBar(
    entry: StandardSong,
    isAlbum: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val density = LocalDensity.current


    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = DragValue.Start,
            positionalThreshold = { distance: Float -> distance * 0.95f },
            velocityThreshold = { with(density) { 5000.dp.toPx() } },
            animationSpec = tween(),
        ).apply {
            updateAnchors(
                DraggableAnchors {
                    DragValue.Start at 0f
                    DragValue.End at 200f
                }
            )
        }
    }

    Box(

    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = anchoredDraggableState
                            .requireOffset()
                            .roundToInt(), y = 0
                    )
                }
                .anchoredDraggable(anchoredDraggableState, orientation = Orientation.Horizontal)
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.clickable { onClick() }
                ) {
                    if (!isAlbum) {
                        AsyncImage(
                            model = "http://lucasanimalfacts.com:4533/rest/getCoverArt?u=lucas&p=ZPvl(%3CD-W6rj[Cb%22&v=1.16.1&c=navidrome&f=json&id=" + entry.coverArt,
                            contentDescription = "Song cover art",
                            modifier = Modifier
                                .width(50.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = entry.title,
                            fontFamily = robotoBoldFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.width(300.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = entry.artist,
                            fontFamily = robotoBoldFamily,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            modifier = Modifier.width(300.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                }
            }
        }
    }
}