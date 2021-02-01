package com.gratus.petservice.service

import com.gratus.petservice.model.request.AnalysisRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.request.UserRequest
import com.gratus.petservice.model.response.*
import com.gratus.petservice.util.constants.ServiceConstants
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AnalysisService {
    @POST(ServiceConstants.ANALYSIS_TODAY_URL)
    fun getAnalysisToday(@Body analysisRequest: AnalysisRequest): Single<AnalysisResponse>
    @POST(ServiceConstants.ANALYSIS_WEEK_URL)
    fun getAnalysisWeek(@Body analysisRequest: AnalysisRequest): Single<AnalysisResponse>
}