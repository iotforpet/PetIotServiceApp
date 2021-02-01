package com.gratus.petservice.service

import com.gratus.petservice.model.request.GetInfoRequest
import com.gratus.petservice.model.request.GetServiceInfoRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.UpdateSensorFrequencyRequest
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.model.response.GetServiceInfoResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface GetServiceInfoService {
    @POST(ServiceConstants.GET_SERVICE_INFO)
    fun getServiceInfoService(@Body getServiceInfoRequest: GetServiceInfoRequest): Single<GetServiceInfoResponse>
    @POST(ServiceConstants.UPDATE_SENSOR_FREQ__URL)
    fun getUpdateServiceInfoService(@Body updateSensorFrequencyRequest: UpdateSensorFrequencyRequest): Single<ProfileResponse>
}