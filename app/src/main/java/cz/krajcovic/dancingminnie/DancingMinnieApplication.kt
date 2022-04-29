package cz.krajcovic.dancingminnie

import android.app.Application
import javax.inject.Inject

//@HiltAndroidApp
@Deprecated("mrkni na TalsecApplication")
class DancingMinnieApplication @Inject constructor(): Application() {
    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
    var appComponentGraph: AppComponentGraph =  DaggerAppComponentGraph.create()





}