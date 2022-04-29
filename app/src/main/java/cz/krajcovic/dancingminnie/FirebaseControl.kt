package cz.krajcovic.dancingminnie

import android.content.DialogInterface
import android.os.Bundle
import android.widget.CompoundButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class FirebaseControl(private var isActive: Boolean = true) {

    /**
     * The `FirebaseAnalytics` used to record screen views.
     */
    // [START declare_analytics]
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    data class Story(private var id: Integer, private var type: String) {
        fun getId(): Integer {
            return id
        }

        fun getType(): String? {
            return type
        }

    }

    fun init() {
        // [START shared_app_measurement]
        // Obtain the FirebaseAnalytics instance.
        if(isActive()) {
            firebaseAnalytics = Firebase.analytics
        }
    }

    /**
     * Return the title of the currently displayed image.
     *
     * @return title of image
     */
    private fun getCurrentImageTitle(): String {
//        val position = binding.viewPager.currentItem
//        val info = IMAGE_INFOS[position]
//        return getString(info.title)
        return "ImageTitle"
    }

    /**
     * Return the id of the currently displayed image.
     *
     * @return id of image
     */
    private fun getCurrentImageId(): String {
//        val position = binding.viewPager.currentItem
//        val info = IMAGE_INFOS[position]
//        return getString(info.id)
        return "ImageId"
    }

//    fun onSceneView(scene: SceneFragment) {
//        if (isEnabled()) {
//            val bundle = Bundle()
//            bundle.putString("scene_simple_class", scene.getClass().getSimpleName())
//            bundle.putString("scene_class", scene.getClass().getName())
//            analytics.logEvent("scene_view", bundle)
//        }
//    }

    fun onClick(dialog: DialogInterface, which: Int) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.VALUE, "Erase settings canceled")
            firebaseAnalytics.logEvent("app_menu", bundle)
            dialog.dismiss()
        }
    }

    private fun isActive(): Boolean {
        return isActive
    }

    fun trackPage(page: String?) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, page)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        }
    }

    fun trackLogin(user: String?) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, user)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
        }
    }

    fun trackEvent(action: String?) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)
        }
    }

    fun trackDownloadEvent(tag: String?) {
        val bundle = Bundle()
        bundle.putString("tag", tag)
        firebaseAnalytics.logEvent("Download", bundle)
    }

    fun trackNavigateEvent(action: String?, story: Story) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, story.getId().toString())
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action)
            firebaseAnalytics.logEvent("Navigate", bundle)
        }
    }

    fun trackVoteEvent(action: String?, story: Story) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, story.getId().toString())
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action)
            firebaseAnalytics.logEvent("Vote", bundle)
        }
    }

    fun trackBookmarkEvent(action: String?, story: Story) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, story.getId().toString())
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action)
            firebaseAnalytics.logEvent("Bookmark", bundle)
        }
    }

    fun trackShareEvent(action: String?, story: Story) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, story.getId().toString())
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle)
        }
    }

    fun trackStory(page: String?, story: Story) {
        if (isActive() && firebaseAnalytics != null) {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, story.getId().toString())
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, story.getType())
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, page)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle)
        }
    }

//    fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        //WatchfaceActivity.this.send_on_battery_change = isChecked;
//        Prefs.putBoolean(Constants.PREF_WATCHFACE_SEND_BATTERY_CHANGE, isChecked)
//        val bundle = Bundle()
//        bundle.putBoolean("value", isChecked)
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.GREATFIT_BATTERY, bundle)
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.GREATFIT_ALARM_TOGGLE, bundle);
//    }
//
//    fun appStarted(context: Context?) {
//        try {
//            if (!checkIsAnalyticsEnabled(context)) {
//                return
//            }
//            val analytics = FirebaseAnalytics.getInstance(context)
//            analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
//        } catch (ex: Throwable) {
//            // Don't fail because analytics
//        }
//    }
//
//    fun screenshot() {
//        execCommandInternally(ShellCommandHelper.getScreenshot())
//        firebaseAnalytics.logEvent(FirebaseEvents.SCREENSHOT, null)
//    }
//
//    fun screenshot() {
//        execCommandInternally(ShellCommandHelper.getScreenshot())
//        firebaseAnalyticslogEvent(FirebaseEvents.SCREENSHOT, null)
//    }
//
//    fun setAdmin() {
//        execCommandInternally(ShellCommandHelper.getDPM())
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.SHELL_COMMAND_ENABLE_ADMIN, null)
//    }
//
//    fun enableAppsList() {
//        execCommandInternally(ShellCommandHelper.getEnableAppsList())
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.SHELL_COMMAND_ENABLE_APPS_LIST, null)
//    }
//
//    fun restartLauncher() {
//        execCommandInternally(ShellCommandHelper.getForceStopHuamiLauncher())
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.SHELL_COMMAND_RESTART_LAUNCHER, null)
//    }
//
//    fun disableAppList() {
//        execCommandInternally(ShellCommandHelper.getDisableAppsList())
//        firebaseAnalytics
//            .logEvent(FirebaseEvents.SHELL_COMMAND_DISABLE_APPS_LIST, null)
//    }


    /**
     * Record a screen view for the visible [ImageFragment] displayed
     * inside [FragmentPagerAdapter].
     */
    fun recordAppOpen() {
//        val id = getCurrentImageId()
//        val name = getCurrentImageTitle()

        // [START image_view_event]
        if (isActive() && firebaseAnalytics != null) {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
        }
        // [END image_view_event]
    }
}