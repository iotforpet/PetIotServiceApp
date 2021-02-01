package com.gratus.petservice.service

import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.request.UserRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.TelUserResponse
import com.gratus.petservice.model.response.UserResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AdminService {
    @POST(ServiceConstants.GET_USER)
    fun getUsers(@Body userRequest: UserRequest): Single<UserResponse>

    @POST(ServiceConstants.ADD_USER)
    fun addUsers(@Body userRequest: UserRequest): Single<ProfileResponse>

    @POST(ServiceConstants.REMOVE_USER)
    fun deleteUsers(@Body userRequest: UserRequest): Single<ProfileResponse>
}