package cz.krajcovic.dancingminnie.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import javax.inject.Inject

class LongRunningService @Inject constructor() : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
        return null
    }
}