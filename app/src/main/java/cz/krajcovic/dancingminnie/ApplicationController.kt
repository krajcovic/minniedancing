package cz.krajcovic.dancingminnie

import android.app.Application

class ApplicationController : Application() {
    var isDebug: Boolean = true

    override fun onCreate() {
        super.onCreate()
        isDebug = BuildConfig.DEBUG
    }
}