package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityForgotPasswordBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.ResetPasswordViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class ResetPasswordActivity:BaseActivity() {
    lateinit var activityForgotPasswordBinding: ActivityForgotPasswordBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var resetPasswordViewModel: ResetPasswordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgotPasswordBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_forgot_password
        )
        resetPasswordViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ResetPasswordViewModel::class.java)
        activityForgotPasswordBinding.resetPasswordViewModel = resetPasswordViewModel
        activityForgotPasswordBinding.setLifecycleOwner(this)
        resetPasswordViewModel.resetPasswordRequest.setUsername(getPrefs()!!.getUsername())
        resetPasswordViewModel.resetPasswordRequest.getProgressVisibility(false)
        resetPasswordViewModel.resetPasswordRequest.getButtonVisibility(true)
        resetPasswordViewModel.loginResponses.observe(this, Observer {
                hideKeyboard()
                getDelay()
                if (it.success) {
                    getPrefs()!!.setUsername(
                        resetPasswordViewModel.resetPasswordRequest.getUsername()
                    )
                    getPrefs()!!.setPassword(
                        resetPasswordViewModel.resetPasswordRequest.getPassword()
                    )
                    DynamicToast.makeSuccess(
                        this@ResetPasswordActivity,
                        it.message
                    )
                        .show()
                    intentActivity()
                } else {
                    if (it.status === NETWORK_CODE_EXP) {
                        showSnack(true, activityForgotPasswordBinding.parent)
                    } else {
                        DynamicToast.makeError(
                            this@ResetPasswordActivity,
                            it.message
                        ).show()
                    }
                }
            })
        activityForgotPasswordBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
    }


    private fun getDelay() {
        val handler = Handler()
        handler.postDelayed({
            resetPasswordViewModel.resetPasswordRequest.getProgressVisibility(false)
            resetPasswordViewModel.resetPasswordRequest.getButtonVisibility(true)
        }, 100)
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected, activityForgotPasswordBinding.parent)
        setIntial(false)
    }

    private fun intentActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}