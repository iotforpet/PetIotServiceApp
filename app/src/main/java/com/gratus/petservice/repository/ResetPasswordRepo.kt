package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.ResetPasswordRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.ResetPasswordService
import io.reactivex.Single
import javax.inject.Inject

class ResetPasswordRepo @Inject constructor(private var resetPasswordService: ResetPasswordService) {
    fun getLoginResponse(resetPasswordRequest: ResetPasswordRequest): Single<LoginResponse> {
        return resetPasswordService.resetPasswordService(resetPasswordRequest)
    }
}