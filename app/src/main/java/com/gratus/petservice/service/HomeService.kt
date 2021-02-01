package com.gratus.petservice.service

import com.gratus.petservice.model.request.*
import com.gratus.petservice.model.response.HomeRespoonse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeService {
    @POST(ServiceConstants.HOME__URL)
    fun home(@Body homeRequest: HomeRequest): Single<HomeRespoonse>
}