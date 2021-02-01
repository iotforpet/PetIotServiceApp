package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.LoginService
import io.reactivex.Single
import javax.inject.Inject

class LoginRepo @Inject constructor(private var loginService: LoginService) {
    fun getLoginResponse(loginRequest: LoginRequest): Single<LoginResponse> {
        return loginService.loginService(loginRequest)
    }
}