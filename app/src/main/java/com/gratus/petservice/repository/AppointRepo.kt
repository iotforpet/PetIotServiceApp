package com.gratus.petservice.repository

import com.gratus.petservice.model.request.AppointRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.ResetPasswordRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.AppointService
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.ResetPasswordService
import com.gratus.petservice.service.SignUpService
import io.reactivex.Single
import javax.inject.Inject

class AppointRepo @Inject constructor(private var appointService: AppointService) {
    fun setAppointResponse(appointRequest: AppointRequest): Single<LoginResponse> {
        return appointService.appoint(appointRequest)
    }
}