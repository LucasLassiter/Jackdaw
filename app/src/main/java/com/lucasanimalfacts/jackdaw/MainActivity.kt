package com.lucasanimalfacts.jackdaw

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.robotoBoldFamily
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailEvent
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen
import com.lucasanimalfacts.jackdaw.navigation.SetupNavGraph
import com.lucasanimalfacts.jackdaw.ui.BottomNavItem
import com.lucasanimalfacts.jackdaw.ui.theme.JackdawTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.last
import kotlin.IllegalArgumentException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var sharedViewModel: PlaylistDetailViewModel
    private lateinit var songSharedViewModel: SongDetailViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        sharedViewModel = ViewModelProvider(this)[PlaylistDetailViewModel::class.java]
        songSharedViewModel = ViewModelProvider(this)[SongDetailViewModel::class.java]

        val channel = NotificationChannel(
            "music_channel",
            "Music notifications",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)



        setContent {
            navController = rememberNavController()

//            val currentDestination = remember { navController.getBackStackEntry(Screen.SongDetail.route) }
            JackdawTheme {
                // A surface container using the 'background' color from the theme

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        bottomBar = {
                            if (songSharedViewModel.sharedState.value.focused) {
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Log.d(
                                        "navControllerDest",
                                        "This " + songSharedViewModel.sharedState.value.focused
                                    )
                                    if (songSharedViewModel.sharedState.value.focused && songSharedViewModel.sharedState.value.song?.artist?.isNotEmpty() == true) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth()
                                                .height(75.dp)
                                                .clickable { navController.navigate(Screen.SongDetail.route) },
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            AsyncImage(
                                                model = songSharedViewModel.sharedState.value.albumArtUrl,
                                                contentDescription = "sdf",
                                                modifier = Modifier.height(50.dp)
                                                    .padding(start = 12.dp)
                                                    .clip(RoundedCornerShape(4.dp)),
                                            )
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .padding(
                                                        top = 11.dp,
                                                        bottom = 12.dp,
                                                        start = 8.dp
                                                    ),
                                                verticalArrangement = Arrangement.SpaceAround
                                            ) {
                                                Text(
                                                    text = songSharedViewModel.sharedState.value.title,
                                                    fontFamily = robotoBoldFamily,
                                                    fontWeight = FontWeight.Normal,
                                                    modifier = Modifier.width(250.dp),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Text(
                                                    text = songSharedViewModel.sharedState.value.song!!.artist,
                                                    fontFamily = robotoBoldFamily,
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 14.sp,
                                                    modifier = Modifier.width(250.dp),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                            FilledIconButton(
                                                onClick = {
                                                    if (songSharedViewModel.sharedState.value.playing) {
                                                        Log.d("playingornah", "Pause")
                                                        songSharedViewModel.onEvent(
                                                            SongDetailEvent.playPause(
                                                                false
                                                            )
                                                        )
                                                    } else {
                                                        Log.d("playingornah", "Play")
                                                        songSharedViewModel.onEvent(
                                                            SongDetailEvent.playPause(
                                                                true
                                                            )
                                                        )
                                                    }
                                                },
                                                modifier = Modifier.size(50.dp)
                                            ) {
                                                Icon(
                                                    if (!songSharedViewModel.sharedState.value.playing) painterResource(
                                                        R.drawable.baseline_play_arrow_24
                                                    ) else painterResource(
                                                        R.drawable.baseline_pause_24
                                                    ),
                                                    contentDescription = "Play / Pause",
                                                    modifier = Modifier.size(25.dp)
                                                )
                                            }
                                        }
                                    }
                                    BottomNavigationBar(items = listOf(
                                        BottomNavItem(
                                            name = "Home",
                                            route = "home",
                                            icon = Icons.Default.Home
                                        ),
                                        BottomNavItem(
                                            name = "Playlists",
                                            route = "playlists",
                                            icon = Icons.Default.List
                                        ),
                                        BottomNavItem(
                                            name = "Settings",
                                            route = "settings",
                                            icon = Icons.Default.Settings
                                        )
                                    ),
                                        navController = navController,
                                        onItemClick = {
                                            navController.navigate(it.route)
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.padding(
                                0.dp,
                                innerPadding.calculateTopPadding(),
                                0.dp,
                                innerPadding.calculateBottomPadding()
                            )
                        ) {
                            SetupNavGraph(navController = navController, sharedViewModel = sharedViewModel, songSharedViewModel = songSharedViewModel, lifecycleOwner = this@MainActivity)
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomNavigationBar(
        items: List<BottomNavItem>,
        navController: NavController,
        modifier: Modifier = Modifier,
        onItemClick: (BottomNavItem) -> Unit
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 5.dp
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    ),

                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgedBox(badge = {
                                    Text(text = item.badgeCount.toString())
                                }
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                )
            }
        }
    }
}


