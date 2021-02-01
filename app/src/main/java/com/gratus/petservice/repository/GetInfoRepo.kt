package com.gratus.petservice.repository

import com.gratus.petservice.model.request.GetInfoRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.GetInfoService
import com.gratus.petservice.service.LoginService
import io.reactivex.Single
import javax.inject.Inject

class GetInfoRepo @Inject constructor(private var getInfoService: GetInfoService) {
    fun getLoginResponse(getInfoRequest: GetInfoRequest): Single<GetInfoResponse> {
        return getInfoService.getInfoService(getInfoRequest)
    }
}