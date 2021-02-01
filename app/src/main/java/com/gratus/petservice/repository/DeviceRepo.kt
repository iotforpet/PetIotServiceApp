package com.gratus.petservice.repository

import com.gratus.petservice.model.request.DeviceRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.UpdateDeviceRequest
import com.gratus.petservice.model.response.DeviceStatusRespoonse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.service.DeviceService
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.SignUpService
import io.reactivex.Single
import javax.inject.Inject

class DeviceRepo @Inject constructor(private var deviceService: DeviceService) {
    fun getDeviceStatus(deviceRequest: DeviceRequest): Single<DeviceStatusRespoonse> {
        return deviceService.deviceService(deviceRequest)
    }
    fun setUpdateDeviceStatus(updateDeviceRequest: UpdateDeviceRequest): Single<ProfileResponse> {
        return deviceService.updateDeviceService(updateDeviceRequest)
    }
}