package com.example.jackdaw

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.net.URI
import java.util.concurrent.TimeUnit


class RetrieveAllSongs {

    val audioList: ArrayList<String> = ArrayList()
    val audioListUri: ArrayList<Uri> = ArrayList()

    public fun retrieveAllSongs(context: Context) {

        val audioURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE
        )

        val selection = "${MediaStore.Audio.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(
            TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS).toString()
        )


        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

        val query = context.contentResolver.query(
            audioURI,
            projection,
            null,
            null,
            null
        )


        query?.use{cursor ->
            // Cache column indicies
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)


            while (cursor.moveToNext())
            {
                // Get values of columns for a given video
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getInt(durationColumn)
                val size = cursor.getInt(sizeColumn)


                val contextUri: Uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                audioList += name
                audioListUri += contextUri

            }
        }
    }

    public fun getAllSongsURI(): ArrayList<Uri> {
        return audioListUri
    }

    public fun getAllSongsNames(): ArrayList<String> {
        return audioList
    }
}