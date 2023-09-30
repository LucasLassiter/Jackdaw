package com.lucasanimalfacts.jackdaw.feature_mainapp.presentation.song_detail

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.lucasanimalfacts.jackdaw.core.service.MusicService
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SeekBarViewModel @Inject constructor(
    private val useCaseWrapper: UseCaseWrapper,
    private val application: Application,
) : AndroidViewModel(application) {

    private lateinit var musicPlayerService: MusicService

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicPlayerBinder
            Log.d("SeekBarViewModel", "inside")
            musicPlayerService = binder.getService()
            // You can now call methods on musicPlayerService
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // Handle disconnection
        }
    }

    private val _curPlayerPosition = mutableStateOf(SeekBarState())
    val curPlayerPosition: State<SeekBarState> = _curPlayerPosition

    init {
        Log.d("SeekBarViewModel", "wow")
        Intent(this.application.applicationContext, MusicService::class.java).also { intent ->
            application.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        updateCurrentPlayerPosition()
    }

    private fun playerPositionFormatter(pos: Int) : String
    {
        var minutes: Int = 0
        var seconds = 0
        val mPos: Int = pos / 1000
        if (mPos > 60) {
            minutes = mPos / 60
            seconds = mPos - (minutes * 60)
        } else {
            seconds = pos / 1000
        }
        return "${minutes}:${if(seconds < 10) "0$seconds" else seconds}"
    }

    private fun updateCurrentPlayerPosition() {
        Log.d("seekbarviewmodel2", "reached")
        viewModelScope.launch {
            delay(100L)
            while(true) {
                val pos = musicPlayerService.mediaPlayer()?.currentPosition
                if(curPlayerPosition.value.unformattedTime.toInt() != pos) {
                    if (pos != null) {
                        _curPlayerPosition.value = curPlayerPosition.value.copy(
                            formatedTime = playerPositionFormatter(pos),
                            unformattedTime = pos.toString()
                        )
                    }
                }
                delay(100L)
            }
        }
    }
}