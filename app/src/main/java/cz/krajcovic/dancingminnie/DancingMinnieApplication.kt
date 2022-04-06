package cz.krajcovic.dancingminnie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DancingMinnieApplication @Inject constructor(): Application() {
    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
    var appGraph: AppGraph =  DaggerAppGraph.create() 





}