package com.gratus.petservice.repository

import com.gratus.petservice.model.request.*
import com.gratus.petservice.model.response.HomeRespoonse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.service.*
import io.reactivex.Single
import javax.inject.Inject

class HomeRepo @Inject constructor(private var homeService: HomeService) {
    fun setAppointResponse(homeRequest: HomeRequest): Single<HomeRespoonse> {
        return homeService.home(homeRequest)
    }
}