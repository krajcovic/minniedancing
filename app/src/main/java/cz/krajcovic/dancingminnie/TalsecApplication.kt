package cz.krajcovic.dancingminnie

import android.app.Application
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TalsecApplication @Inject constructor() : Application(), ThreatListener.ThreatDetected,
    ThreatListener.DeviceState {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
    var appComponentGraph: AppComponentGraph = DaggerAppComponentGraph.create()
    var firebaseControl = FirebaseControl(false)

    override fun onCreate() {
        super.onCreate()

        // Uncomment the following Log.e(...) to get your expectedSigningCertificateHashBase64
        // Copy the result from logcat and assign to expectedSigningCertificateHashBase64
        Timber.e("SigningCertificateHash", expectedSigningCertificateHashBase64)

        val config = TalsecConfig(
            expectedPackageName,
            expectedSigningCertificateHashBase64,
            watcherMail,
            supportedAlternativeStores
        )

        ThreatListener(this, this).registerListener(this)
        Talsec.start(this, config)

//        FirebaseApp.initializeApp(applicationContext)

        firebaseControl.init()
        firebaseControl.recordAppOpen()

    }

    override fun onRootDetected() {
        Timber.e("onRootDetected()")
    }

    override fun onDebuggerDetected() {
        Timber.e("onDebuggerDetected()")
    }

    override fun onEmulatorDetected() {
        Timber.e("onEmulatorDetected()")
    }

    override fun onTamperDetected() {
        Timber.e("onTamperDetected()")
    }

    override fun onUntrustedInstallationSourceDetected() {
        Timber.e("onUntrustedInstallationSourceDetected()")
    }

    override fun onHookDetected() {
        Timber.e("onHookDetected()")
    }

    override fun onDeviceBindingDetected() {
        Timber.e("onDeviceBindingDetected()")
    }

    override fun onUnlockedDeviceDetected() {
        // Set your reaction
        Timber.w("onUnlockedDeviceDetected")
    }

    override fun onHardwareBackedKeystoreNotAvailableDetected() {
        // Set your reaction
        Timber.w("onHardwareBackedKeystoreNotAvailableDetected")
    }


    companion object {
        private const val expectedPackageName =
            "com.aheaditec.talsec.demoapp" // Don't use Context.getPackageName!
        private const val expectedSigningCertificateHashBase64 =
            "mVr/qQLO8DKTwqlL+B1qigl9NoBnbiUs8b4c2Ewcz0k=" // Replace with your release (!) signing certificate hash
        private const val watcherMail = "dusan.krajcovic@gmail.com" // for Alerts and Reports
        private val supportedAlternativeStores = arrayOf(
            // Google Play Store and Huawei AppGallery are supported out of the box, you can pass empty array or null or add other stores like the Samsung's one:
            "com.sec.android.app.samsungapps" // Samsung Store
        )
    }
}