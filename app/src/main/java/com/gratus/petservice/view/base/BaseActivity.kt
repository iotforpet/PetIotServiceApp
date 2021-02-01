package com.gratus.petservice.view.base

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.gratus.petservice.R
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.util.networkManager.NetworkOnlineCheck
import com.gratus.petservice.util.networkManager.NetworkOnlineReceiver
import com.gratus.petservice.util.pref.AppPreferencesHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var prefs: AppPreferencesHelper

    @Inject
    lateinit var networkOnlineCheck: NetworkOnlineCheck
    private var snackbar: Snackbar? = null
    private var initial: Boolean = false
    fun isNetworkConnected(): Boolean {
        return networkOnlineCheck.isNetworkOnline
    }

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            var imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 2)
        }
    }

    override fun onResume() {
        super.onResume()
       // hideSystemUI()
    }

    internal fun networkReceiver() {
        val myReceiver = NetworkOnlineReceiver()
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(myReceiver, filter)
    }

    internal fun networkCheck(view: View) {
        val intentFilter =
            IntentFilter(AppConstants.NETWORK_AVAILABLE_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                var isNetworkAvailable =
                    intent.getBooleanExtra(AppConstants.IS_NETWORK_AVAILABLE, false)
                if (!isNetworkAvailable) {
                    initial = true
                }
                if (initial) {
                    showSnack(isNetworkAvailable, view)
                    initial = isNetworkAvailable
                }
                initial = true
            }
        }, intentFilter)
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

    override fun onPause() {
        super.onPause()
        val activityManager = applicationContext
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.moveTaskToFront(taskId, 0)
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
    open fun setIntial(intial: Boolean) {
        initial = intial
    }
}