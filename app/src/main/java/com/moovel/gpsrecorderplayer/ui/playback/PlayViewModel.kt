package com.moovel.gpsrecorderplayer.ui.playback

import android.app.Application
import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moovel.gpsrecorderplayer.repo.IPlayService
import com.moovel.gpsrecorderplayer.repo.PlayService
import com.moovel.gpsrecorderplayer.repo.Record
import com.moovel.gpsrecorderplayer.utils.switchMap

class PlayViewModel(application: Application) : AndroidViewModel(application) {

    private val service: MutableLiveData<IPlayService?> = MutableLiveData()
    val location = service.switchMap { it?.locations() }
    val signal = service.switchMap { it?.signal() }
    val playing: LiveData<Boolean> = service.switchMap { it?.isPlaying() }

    init {
        val recordServiceIntent = Intent(application, PlayService::class.java)
        application.bindService(recordServiceIntent, object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
                service.value = null
            }

            override fun onServiceConnected(p0: ComponentName, binder: IBinder) {
                service.value = PlayService.of(binder)
            }
        }, BIND_AUTO_CREATE)
    }

    fun play(record: Record) {
        service.value?.start(record)
    }

    fun stop() {
        service.value?.stop()
    }
}