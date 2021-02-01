package com.gratus.petservice.service

import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.TelUserResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface TelService {
    @POST(ServiceConstants.TELUSERS__URL)
    fun getTelUsers(@Body telUserRequest: TelUserRequest): Single<TelUserResponse>

    @POST(ServiceConstants.ADDTELUSERS__URL)
    fun addTelUsers(@Body telUserRequest: TelUserRequest): Single<ProfileResponse>

    @POST(ServiceConstants.DELETETELUSERS__URL)
    fun deleteTelUsers(@Body telUserRequest: TelUserRequest): Single<ProfileResponse>
}