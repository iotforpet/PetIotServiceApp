package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.SignUpService
import io.reactivex.Single
import javax.inject.Inject

class SignUpRepo @Inject constructor(private var signUpService: SignUpService) {
    fun getSignUpResponse(registrationRequest: RegistrationRequest): Single<LoginResponse> {
        return signUpService.signUp(registrationRequest)
    }
    fun getEditResponse(registrationRequest: RegistrationRequest): Single<LoginResponse> {
        return signUpService.editProfile(registrationRequest)
    }
}