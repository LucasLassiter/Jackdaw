package com.lucasanimalfacts.jackdaw.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lucasanimalfacts.jackdaw.core.playlist_detail.PlaylistDetailViewModel
import com.lucasanimalfacts.jackdaw.core.song_detail.SongDetailViewModel
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage.HomeScreen
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlist_detail.PlaylistDetailScreen
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.playlists.Playlists
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.settings.Settings
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.song_detail.SongDetailScreen
import com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util.Screen

@Composable
fun SetupNavGraph(navController: NavHostController, sharedViewModel: PlaylistDetailViewModel, songSharedViewModel: SongDetailViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, sharedViewModel, songSharedViewModel)
        }
        composable(Screen.Playlists.route) {
            Playlists(navController = navController, sharedViewModel = sharedViewModel, songSharedViewModel = songSharedViewModel)
        }
        composable(Screen.Settings.route) {
            Settings()
        }
        composable(Screen.PlaylistsDetail.route) {
            PlaylistDetailScreen(sharedViewModel = sharedViewModel, songSharedViewModel, navController = navController)
        }
        composable(Screen.SongDetail.route) {
            SongDetailScreen(viewModel = songSharedViewModel, navController = navController)
        }
    }
}