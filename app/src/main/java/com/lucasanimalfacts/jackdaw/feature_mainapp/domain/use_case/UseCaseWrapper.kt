package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case

data class UseCaseWrapper(
    val getRandomSongs: GetRandomSongs,
    val getStarred: GetStarred,
    val getAlbumArt: GetAlbumArt,
    val getPlaylists: GetPlaylists,
    val getPlaylist: GetPlaylist
)
