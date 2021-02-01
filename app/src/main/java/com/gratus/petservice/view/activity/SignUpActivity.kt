package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivitySignUpBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.SignUpViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class SignUpActivity:BaseActivity() {
    lateinit var activitySignUpBinding: ActivitySignUpBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var signUpViewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySignUpBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_sign_up
        )
        signUpViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)
        activitySignUpBinding.signUpViewModel = signUpViewModel
        activitySignUpBinding.setLifecycleOwner(this)
        signUpViewModel.registrationRequest.getEmailEnable(true)
        signUpViewModel.registrationRequest.getProgressVisibility(false)
        signUpViewModel.registrationRequest.getButtonVisibility(true)
        signUpViewModel.loginResponses.observe(this, Observer {
                hideKeyboard()
                getDelay()
                if (it.success) {
                    DynamicToast.makeSuccess(
                        this@SignUpActivity,
                        it.message
                    )
                        .show()
                    intentActivity()
                } else {
                    if (it.status === NETWORK_CODE_EXP) {
                        showSnack(true, activitySignUpBinding.parent)
                    } else {
                        DynamicToast.makeError(
                            this@SignUpActivity,
                            it.message
                        ).show()
                    }
                }
            })
        activitySignUpBinding.backArrowImg.setOnClickListener { v -> intentActivity() }
        activitySignUpBinding.loginBt.setOnClickListener { v -> intentActivity() }
    }


    private fun getDelay() {
        val handler = Handler()
        handler.postDelayed({
            signUpViewModel.registrationRequest.getProgressVisibility(false)
            signUpViewModel.registrationRequest.getButtonVisibility(true)
        }, 100)
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected, activitySignUpBinding.parent)
        setIntial(false)
    }

    private fun intentActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        intentActivity()
    }
}