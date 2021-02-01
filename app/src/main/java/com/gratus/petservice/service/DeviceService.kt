package com.gratus.petservice.service

import com.gratus.petservice.model.request.DeviceRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.request.UpdateDeviceRequest
import com.gratus.petservice.model.response.DeviceStatusRespoonse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface DeviceService {
    @POST(ServiceConstants.DEVICE_STATUS__URL)
    fun deviceService(@Body deviceRequest: DeviceRequest): Single<DeviceStatusRespoonse>
    @POST(ServiceConstants.UPDATE_DEVICE_STATUS__URL)
    fun updateDeviceService(@Body updateDeviceRequest: UpdateDeviceRequest): Single<ProfileResponse>
}