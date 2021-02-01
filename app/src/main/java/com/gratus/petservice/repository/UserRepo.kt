package com.gratus.petservice.repository

import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.request.UserRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.TelUserResponse
import com.gratus.petservice.model.response.UserResponse
import com.gratus.petservice.service.AdminService
import com.gratus.petservice.service.LoginService
import com.gratus.petservice.service.SignUpService
import com.gratus.petservice.service.TelService
import io.reactivex.Single
import javax.inject.Inject

class UserRepo @Inject constructor(private var adminService: AdminService) {
    fun getUsersResponse(userRequest: UserRequest): Single<UserResponse> {
        return adminService.getUsers(userRequest)
    }
    fun getAddResponse(userRequest: UserRequest): Single<ProfileResponse> {
        return adminService.addUsers(userRequest)
    }
    fun getDeleteResponse(userRequest: UserRequest): Single<ProfileResponse> {
        return adminService.deleteUsers(userRequest)
    }
}