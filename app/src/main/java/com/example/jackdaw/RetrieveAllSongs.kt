package com.example.jackdaw

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import java.util.concurrent.TimeUnit


class RetrieveAllSongs {

    public fun retrieveAllSongs(context: Context): ArrayList<String> {
        val audioList: ArrayList<String> = ArrayList()

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

        val audioCursor: Cursor? = context.contentResolver.query(
            audioURI,
            projection,
            null,
            null,
            sortOrder
        )

        if(audioCursor != null)
        {
            Log.d("Errors", "0")
            if(audioCursor.moveToFirst())
            {
                Log.d("Errors", "1")
                do{
                    Log.d("Errors", "2")
                    val audioIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                    Log.d("Errors", "3")
                    audioList.add(audioCursor.getString(audioIndex))
                    Log.d("Errors", "4")
                }while(audioCursor.moveToNext())
            }
        }

        audioCursor?.close()

        return audioList
    }
}