package com.lucasanimalfacts.jackdaw.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lucasanimalfacts.jackdaw.core.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.HomeScreen
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.PlaylistDetailScreen
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.Playlists
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.PlaylistsViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.settings.Settings
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun SetupNavGraph(navController: NavHostController, sharedViewModel: PlaylistDetailViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Playlists.route) {
            Playlists(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(Screen.Settings.route) {
            Settings()
        }
        composable(Screen.PlaylistsDetail.route) {
            PlaylistDetailScreen(sharedViewModel = sharedViewModel)
        }
    }
}