package com.gratus.petservice.service

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {
    @POST(ServiceConstants.PROFILE__URL)
    fun profileService(@Body profileRequest: ProfileRequest): Single<ProfileResponse>
}