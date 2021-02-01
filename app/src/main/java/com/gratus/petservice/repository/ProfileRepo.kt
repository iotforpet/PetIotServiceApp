package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.ProfileService
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepo @Inject constructor(private var profileService: ProfileService) {
    fun getProfileResponse(profileRequest: ProfileRequest): Single<ProfileResponse> {
        return profileService.profileService(profileRequest)
    }
}