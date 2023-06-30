package com.lucasanimalfacts.jackdaw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.lucasanimalfacts.jackdaw.core.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.HomepageViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.PlaylistsViewModel
import com.lucasanimalfacts.jackdaw.navigation.SetupNavGraph
import com.lucasanimalfacts.jackdaw.ui.BottomNavItem
import com.lucasanimalfacts.jackdaw.ui.theme.JackdawTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var sharedViewModel: PlaylistDetailViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(this)[PlaylistDetailViewModel::class.java]

        setContent {
            JackdawTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Playlists",
                                    route = "playlists",
                                    icon =  Icons.Default.List
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
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.padding(
                                0.dp,
                                innerPadding.calculateTopPadding(),
                                0.dp,
                                innerPadding.calculateBottomPadding()
                            )
                        ) {
                            SetupNavGraph(navController = navController, sharedViewModel = sharedViewModel)
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
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}


