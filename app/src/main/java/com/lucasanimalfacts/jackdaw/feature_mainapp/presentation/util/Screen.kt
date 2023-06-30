package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.util

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Playlists: Screen(route = "playlists")
    object Settings: Screen(route = "settings")
    object PlaylistsDetail: Screen(route = "playlists_detail")
}
