package com.gratus.petservice.view.base

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.gratus.petservice.R
import com.gratus.petservice.util.networkManager.NetworkOnlineCheck
import com.gratus.petservice.util.pref.AppPreferencesHelper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {
    @Inject
    lateinit var prefs: AppPreferencesHelper

    @Inject
    lateinit var networkOnlineCheck: NetworkOnlineCheck
    private var snackbar: Snackbar? = null
    private var initial: Boolean = false
    fun isNetworkConnected(): Boolean {
        return networkOnlineCheck.isNetworkOnline
    }

    internal fun showSnack(networkConnected: Boolean, parent: View?) {
        if (networkConnected) {
            snackbar = Snackbar.make(parent!!, R.string.network_online, Snackbar.LENGTH_SHORT)
            getSnackBarCustom(snackbar!!.view, true)
        } else {
            snackbar = Snackbar.make(parent!!, R.string.network_offline, Snackbar.LENGTH_INDEFINITE)
            getSnackBarCustom(snackbar!!.view, false)
        }
    }

    internal fun getSnackBarCustom(view: View, b: Boolean) {
        val textView =
                view.findViewById<View>(R.id.snackbar_text) as TextView
        if (b) {
            textView.setTextColor(Color.WHITE)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            snackbar!!.setBackgroundTint(resources.getColor(R.color.theme))
        } else {
            textView.setTextColor(Color.YELLOW)
        }
        snackbar!!.show()
    }

    @JvmName("getPrefs1")
    fun getPrefs(): AppPreferencesHelper? {
        return prefs
    }
}