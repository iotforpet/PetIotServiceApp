package com.gratus.petservice.service

import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST(ServiceConstants.SIGNUP__URL)
    fun signUp(@Body registrationRequest: RegistrationRequest): Single<LoginResponse>

    @POST(ServiceConstants.EDIT_PROFILE__URL)
    fun editProfile(@Body registrationRequest: RegistrationRequest): Single<LoginResponse>
}