package com.gratus.petservice.service

import com.gratus.petservice.model.request.AppointRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.ResetPasswordRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AppointService {
    @POST(ServiceConstants.APPOINT__URL)
    fun appoint(@Body appointRequest: AppointRequest): Single<LoginResponse>
}