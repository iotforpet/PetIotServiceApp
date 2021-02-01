package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.TelUserResponse
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.SignUpService
import com.gratus.petservice.service.TelService
import io.reactivex.Single
import javax.inject.Inject

class TelUserRepo @Inject constructor(private var telService: TelService) {
    fun getTelUsersResponse(telUserRequest: TelUserRequest): Single<TelUserResponse> {
        return telService.getTelUsers(telUserRequest)
    }
    fun getAddTelResponse(telUserRequest: TelUserRequest): Single<ProfileResponse> {
        return telService.addTelUsers(telUserRequest)
    }
    fun getDeleteTelResponse(telUserRequest: TelUserRequest): Single<ProfileResponse> {
        return telService.deleteTelUsers(telUserRequest)
    }
}