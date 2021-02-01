package com.gratus.petservice.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityEditProfileBinding

import com.gratus.petservice.model.common.UserDetails
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.PetDetails
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.EditProfileViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class EditProfileActivity:BaseActivity() {
    lateinit var activityEditProfileBinding: ActivityEditProfileBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var editProfileViewModel: EditProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditProfileBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_edit_profile
        )
        editProfileViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(EditProfileViewModel::class.java)
        activityEditProfileBinding.editProfileViewModel = editProfileViewModel
        activityEditProfileBinding.setLifecycleOwner(this)
        val profileResponse: ProfileResponse? = intent.getSerializableExtra("profile") as ProfileResponse?
        var userDetails: UserDetails? = profileResponse?.userDetails
        var petDetails: PetDetails = profileResponse?.userDetails?.info?.get(0)!!
        if (userDetails != null) {
            editProfileViewModel.registrationRequest=RegistrationRequest(userDetails.e_mail,petDetails.name_of_animal,
                userDetails.phone_number,petDetails.type_of_animal,petDetails.breed,petDetails.age_of_animal,petDetails.sex_of_animal
            )
        }
        editProfileViewModel.registrationRequest.getProgressVisibility(false)
        editProfileViewModel.registrationRequest.getButtonVisibility(true)
        editProfileViewModel.loginResponses.observe(this, Observer {
            hideKeyboard()
            getDelay()
            if (it.success) {
                DynamicToast.makeSuccess(
                        this@EditProfileActivity,
                        it.message
                )
                        .show()
                intentActivity()
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activityEditProfileBinding.parent)
                } else {
                    DynamicToast.makeError(
                            this@EditProfileActivity,
                            it.message
                    ).show()
                }
            }
        })
        activityEditProfileBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
    }


    private fun getDelay() {
        val handler = Handler()
        handler.postDelayed({
            editProfileViewModel.registrationRequest.getProgressVisibility(false)
            editProfileViewModel.registrationRequest.getButtonVisibility(true)
        }, 100)
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
            showSnack(isConnected, activityEditProfileBinding.parent)
        setIntial(false)
    }

    private fun intentActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        intentActivity()
    }
}