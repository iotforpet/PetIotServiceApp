package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityProfileBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.ProfileViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class ProfileActivity:BaseActivity() {
    lateinit var activityProfileBinding: ActivityProfileBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var profileViewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProfileBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_profile
        )
        profileViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
        activityProfileBinding.profileViewModel = profileViewModel
        activityProfileBinding.setLifecycleOwner(this)
        profileViewModel.hitProfile()
        profileViewModel.profileResponseMutableLiveData.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                        this@ProfileActivity,
                        it.message
                )
                        .show()
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activityProfileBinding.parent)
                } else {
                    DynamicToast.makeError(
                            this@ProfileActivity,
                            it.message
                    ).show()
                }
            }
        })
        activityProfileBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
        activityProfileBinding.rlBt.setOnClickListener { v -> intentActivity() }
    }


    fun onNetworkConnectionChanged(isConnected: Boolean) {
            showSnack(isConnected, activityProfileBinding.parent)
        setIntial(false)
    }

    private fun intentActivity() {
        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("profile", profileViewModel.profileResponseMutableLiveData.value)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}