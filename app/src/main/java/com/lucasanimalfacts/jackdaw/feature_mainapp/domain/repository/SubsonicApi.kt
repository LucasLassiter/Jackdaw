package com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository

import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_album.GetAlbumSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlist.GetPlaylistSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_playlists.GetPlaylistsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.models.star.StarSubsonicResponseHolder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SubsonicApi {

    @GET("getRandomSongs")
    suspend fun getRandomSongs(@Query("u") username: String,
                               @Query("p") password: String,
                               @Query("v") version: String,
                               @Query("c") appName: String,
                               @Query("f") responseType: String
    ) : Response<GetRandomSongsSubsonicResponseHolder>

    @GET("getStarred")
    suspend fun getStarred(@Query("u") username: String,
                           @Query("p") password: String,
                           @Query("v") version: String,
                           @Query("c") appName: String,
                           @Query("f") responseType: String
    ) : Response<GetStarredSubsonicResponseHolder>

    @GET("getCoverArt")
    suspend fun getCoverArt(@Query("u") username: String,
                            @Query("p") password: String,
                            @Query("v") version: String,
                            @Query("c") appName: String,
                            @Query("f") responseType: String,
                            @Query("size") size: String,
                            @Query("id") id: String,
    ) : Response<AlbumArt>

    @GET("getPlaylists")
    suspend fun getPlaylists(@Query("u") username: String,
                             @Query("p") password: String,
                             @Query("v") version: String,
                             @Query("c") appName: String,
                             @Query("f") responseType: String,
    ) : Response<GetPlaylistsSubsonicResponseHolder>

    @GET("getPlaylist")
    suspend fun getPlaylist(@Query("u") username: String,
                            @Query("p") password: String,
                            @Query("v") version: String,
                            @Query("c") appName: String,
                            @Query("f") responseType: String,
                            @Query("id") id: String
    ) : Response<GetPlaylistSubsonicResponseHolder>

    @GET("getAlbum")
    suspend fun getAlbum(@Query("u") username: String,
                         @Query("p") password: String,
                         @Query("v") version: String,
                         @Query("c") appName: String,
                         @Query("f") responseType: String,
                         @Query("id") id: String
    ) : Response<GetAlbumSubsonicResponseHolder>

    @GET("star")
    suspend fun star(
        @Query("u") username: String,
        @Query("p") password: String,
        @Query("v") version: String,
        @Query("c") appName: String,
        @Query("f") responseType: String,
        @Query("id") id: String
    ) : Response<StarSubsonicResponseHolder>

    @GET("unstar")
    suspend fun unStar(
        @Query("u") username: String,
        @Query("p") password: String,
        @Query("v") version: String,
        @Query("c") appName: String,
        @Query("f") responseType: String,
        @Query("id") id: String
    ) : Response<StarSubsonicResponseHolder>
}