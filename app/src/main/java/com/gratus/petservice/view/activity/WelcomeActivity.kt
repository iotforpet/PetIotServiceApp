package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityWelcomeBinding
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.WelcomeViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject


class WelcomeActivity : BaseActivity() {
    lateinit var activityWelcomeBinding: ActivityWelcomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var welcomeViewModel: WelcomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        welcomeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)
        activityWelcomeBinding.welcomeViewModel = welcomeViewModel
        activityWelcomeBinding.setLifecycleOwner(this)
        welcomeViewModel.loginRequest.getProgressVisibility(false)
        welcomeViewModel.loginRequest.getButtonVisibility(true)
        welcomeViewModel.loginResponses.observe(this, Observer {
            if (it.success) {
                if (it.userDetails?.type.equals("admin")) {
                    intentUserActivity()
                } else {
//                    var i: Int  =0
//                    for (key in it.userDetails?.info!!.keys) {
//                        if (i == 0) {
////                            var dsad: String =
////                                it.userDetails?.info?.get(key)?.resource_catalog.toString()
////                            var dsads: String = it.userDetails?.info?.get(key)?.deviceId.toString()

                            if ( it.userDetails?.info?.get(0)?.resource_catalog.toString() != null) {
                                    getPrefs()?.setUsername(welcomeViewModel.loginRequest.username)
                                    getPrefs()?.setPassword(welcomeViewModel.loginRequest.password)
                                    getPrefs()?.setResource(it.userDetails?.info?.get(0)?.resource_catalog.toString())
                                    getPrefs()?.setDeviceId(it.userDetails?.info?.get(0)?.deviceId.toString())
                                    getPrefs()?.setType(it.userDetails?.type)
                                    getPrefs()?.setUserId(it.userDetails?.info?.get(0)?.userID)
                                    getPrefs()?.setAnimalName(it.userDetails?.info?.get(0)?.name_of_animal.toString())
                                welcomeViewModel.hitGetInfo()
                            } else {
                                DynamicToast.makeError(
                                    this@WelcomeActivity,
                                    "User not activated. Please contact admin"
                                )
                                    .show()
                            }
                        }
                        //i++
                    //}
                //}
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityWelcomeBinding.parent)
                } else {
                    getDelay()
                    DynamicToast.makeError(this@WelcomeActivity, it.message)
                        .show()
                }
            }
        })
        welcomeViewModel.getInfoResponse.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(this@WelcomeActivity, "Login successful")
                    .show()
                getPrefs()?.setUsername(welcomeViewModel.loginRequest.username)
                getPrefs()?.setPassword(welcomeViewModel.loginRequest.password)
                getPrefs()?.setDevice(it.getInfo?.Devices?.P1?.DC)
                getPrefs()?.setService(it.getInfo?.Devices?.P1?.SC)
                val gson = Gson()
                val json = gson.toJson(it.getInfo?.Devices?.P1?.Sensors)
                getPrefs()?.setSensorList(json)
                welcomeViewModel.loginRequest.getProgressVisibility(false)
                welcomeViewModel.loginRequest.getButtonVisibility(true)
                intentMainActivity()
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityWelcomeBinding.parent)
                } else {
                    getDelay()
                    DynamicToast.makeError(
                        this@WelcomeActivity,
                        "User not activated. Please contact admin"
                    )
                        .show()
                }
            }
        })
        activityWelcomeBinding.forgotPasswordTv.setOnClickListener { v ->
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        activityWelcomeBinding.signUpTv.setOnClickListener { v ->
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun getDelay() {
        val handler = Handler()
        handler.postDelayed({
            welcomeViewModel.loginRequest.getProgressVisibility(false)
            welcomeViewModel.loginRequest.getButtonVisibility(true)
        }, 100)
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected, activityWelcomeBinding.parent)
        setIntial(false)
    }

    private fun intentMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun intentUserActivity() {
        val intent = Intent(this, UsersActivity::class.java)
        startActivity(intent)
        finish()
    }
}