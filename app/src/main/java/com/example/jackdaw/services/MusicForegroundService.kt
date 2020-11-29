package com.example.jackdaw.services

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast
import android.os.Process

class MusicForegroundService : Service() {
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        fun musicPlayer(context: Context)
        {

        }
    }

    override fun onCreate()
    {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_FOREGROUND).apply {
            start()

            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        startForeground()

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
    }

}