package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_cover_art

import okhttp3.ResponseBody
import retrofit2.Call
import java.io.InputStream

data class AlbumArt(
    val binaryStream: InputStream
)
