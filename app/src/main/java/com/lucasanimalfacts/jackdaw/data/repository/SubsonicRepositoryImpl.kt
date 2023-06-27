package com.lucasanimalfacts.jackdaw.data.repository

import android.util.Log
import com.lucasanimalfacts.jackdaw.domain.models.get_cover_art.AlbumArt
import com.lucasanimalfacts.jackdaw.domain.models.get_starred.GetStarredSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.domain.models.random_songs.GetRandomSongsSubsonicResponseHolder
import com.lucasanimalfacts.jackdaw.domain.repository.RetrofitInstance
import com.lucasanimalfacts.jackdaw.domain.repository.SubsonicRepository
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class SubsonicRepositoryImpl : SubsonicRepository {

    override suspend fun getRandomSongs(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String
    ): GetRandomSongsSubsonicResponseHolder {
        Log.d("getRandomSongs", "here")
        val response = try {
            RetrofitInstance.api.getRandomSongs(username = username, password = password, version = version, appName = appName, responseType = responseType)
        } catch(e: IOException) {
            Log.e("getRandomSongs", "IOException")
            throw Exception("IOException")
        } catch(e: HttpException) {
            Log.e("getRandomSongs", "HTTPException")
            throw Exception("HTTPException")
        }
        if (response.isSuccessful && response.body() != null) {
            Log.d("getRandomSongs", response.body()!!.toString())
        } else {
            Log.e("getRandomSongs", "Response not successful")
        }

        Log.d("getRandomSongs", response.body()!!.toString())
        return response.body()!!
    }

    override suspend fun getStarred(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String
    ): GetStarredSubsonicResponseHolder {
        val response = try {
            RetrofitInstance.api.getStarred(username = username, password = password, version = version, appName = appName, responseType = responseType)
        } catch(e: IOException) {
            Log.e("getRandomSongs", "IOException")
            throw Exception("IOException")
        } catch(e: HttpException) {
            Log.e("getRandomSongs", "HTTPException")
            throw Exception("HTTPException")
        }
        if (response.isSuccessful && response.body() != null) {
            Log.d("getRandomSongs", response.body()!!.toString())
        } else {
            Log.e("getRandomSongs", "Response not successful")
        }

        Log.d("getRandomSongs", response.body()!!.toString())
        return response.body()!!
    }

    override suspend fun getCoverArt(
        username: String,
        password: String,
        version: String,
        appName: String,
        responseType: String,
        id: String
    ): AlbumArt {
        val response = try {
            RetrofitInstance.api.getCoverArt(username = username, password = password, version = version, appName = appName, responseType = responseType, id = id)
        } catch(e: IOException) {
            throw Exception("IOException")
        } catch(e: HttpException) {
            throw Exception("HTTPException")
        }

        Log.d("album art cover", response.body()!!.toString())
        return response.body()!!
    }
}