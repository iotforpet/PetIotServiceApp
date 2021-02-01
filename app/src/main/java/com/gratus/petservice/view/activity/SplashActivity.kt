package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gratus.petservice.BaseApplication
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivitySplashBinding
import com.gratus.petservice.databinding.ActivityWelcomeBinding
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.util.pref.AppPreferencesHelper
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.SplashViewModel
import com.gratus.petservice.viewModel.activity.WelcomeViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class SplashActivity : BaseActivity() {
    lateinit var activitySplashBinding: ActivitySplashBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        //((BaseApplication) getApplicationContext()).getAppComponent().inject(this);
        splashViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        activitySplashBinding.splashViewModel = splashViewModel
        activitySplashBinding.setLifecycleOwner(this)
        splashViewModel!!.hitLogin()
        splashViewModel!!.loginResponseMutableLiveData.observe(this) { it ->

            if (it.success) {
                if(it.userDetails?.type.equals("admin")){
                    intentUserActivity()
                }
                else {
//                    var i: Int  =0
//                    for (key in it.userDetails?.info!!.keys) {
//                        if (i == 0) {
                            if ( it.userDetails?.info?.get(0)?.resource_catalog.toString() != null) {
                                getPrefs()?.setResource(it.userDetails?.info?.get(0)?.resource_catalog.toString())
                                getPrefs()?.setDeviceId(it.userDetails?.info?.get(0)?.deviceId.toString())
                                getPrefs()?.setType(it.userDetails?.type)
                                getPrefs()?.setUserId(it.userDetails?.info?.get(0)?.userID)
                                getPrefs()?.setAnimalName(it.userDetails?.info?.get(0)?.name_of_animal.toString())
                                splashViewModel.hitGetInfo()
                            } else {
                                intentActivity()
                                DynamicToast.makeError(
                                    this@SplashActivity,
                                    "User not activated. Please contact admin"
                                )
                                    .show()
                            }
//                        }
//                        i++
//                    }
                }
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    intentActivity()
                    showSnack(true, activitySplashBinding.parent)
                } else {
                    getDelay()
                    DynamicToast.makeError(this@SplashActivity, it.message)
                        .show()
                }
            }
        }
        splashViewModel.getInfoResponse.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(this@SplashActivity, "Login successful")
                    .show()
                getPrefs()?.setDevice(it.getInfo?.Devices?.P1?.DC)
                getPrefs()?.setService(it.getInfo?.Devices?.P1?.SC)
                val gson = Gson()
                val json = gson.toJson(it.getInfo?.Devices?.P1?.Sensors)
                getPrefs()?.setSensorList(json)
                intentMainActivity()
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activitySplashBinding.parent)
                } else {
                    getDelay()
                    DynamicToast.makeError(this@SplashActivity, "User not activated. Please contact admin")
                        .show()
                }
            }
        })
    }

    private fun getDelay() {
        val handler = Handler()
        handler.postDelayed({
            intentActivity()
        }, 100)
    }
    private fun intentActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun intentMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected, activitySplashBinding.parent)
        setIntial(false)
    }
    private fun intentUserActivity() {
        val intent = Intent(this, UsersActivity::class.java)
        startActivity(intent)
        finish()
    }
}
