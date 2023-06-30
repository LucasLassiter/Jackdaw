package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.homepage

sealed class HomepageEvent {
    data class GetAlbumArt(val id: String): HomepageEvent()
}