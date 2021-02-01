package com.gratus.petservice.service

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(ServiceConstants.WELCOME_URL)
    fun loginService(@Body loginRequest: LoginRequest): Single<LoginResponse>
}