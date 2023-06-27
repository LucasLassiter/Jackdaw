package com.lucasanimalfacts.jackdaw.domain.use_case

data class UseCaseWrapper(
    val getRandomSongs: GetRandomSongs,
    val getStarred: GetStarred,
    val getAlbumArt: GetAlbumArt
)
