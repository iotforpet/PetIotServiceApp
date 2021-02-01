package com.gratus.petservice.repository

import com.gratus.petservice.model.request.GetInfoRequest
import com.gratus.petservice.model.request.GetServiceInfoRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.UpdateSensorFrequencyRequest
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.model.response.GetServiceInfoResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.service.GetInfoService
import com.gratus.petservice.service.GetServiceInfoService
import com.gratus.petservice.service.LoginService
import io.reactivex.Single
import javax.inject.Inject

class GetServiceInfoRepo @Inject constructor(private var getServiceInfoService: GetServiceInfoService) {
    fun getServiceInfoResponse(getServiceInfoRequest: GetServiceInfoRequest): Single<GetServiceInfoResponse> {
        return getServiceInfoService.getServiceInfoService(getServiceInfoRequest)
    }
    fun setUpdateInfoResponse(updateSensorFrequencyRequest: UpdateSensorFrequencyRequest): Single<ProfileResponse> {
        return getServiceInfoService.getUpdateServiceInfoService(updateSensorFrequencyRequest)
    }
}