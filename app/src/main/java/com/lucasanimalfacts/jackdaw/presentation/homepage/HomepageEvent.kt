package com.lucasanimalfacts.jackdaw.presentation.homepage

sealed class HomepageEvent {
    data class GetAlbumArt(val id: String): HomepageEvent()
}