package com.example.jackdaw.services

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MusicForegroundService : Application() {
    val CHANNEL_ID : String = "MusicService"

    @Override
    override fun onCreate()
    {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val  musicChannel : NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        }
    }
}