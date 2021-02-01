package com.gratus.petservice.repository

import com.gratus.petservice.model.request.AnalysisRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.AnalysisResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.service.AnalysisService
import com.gratus.petservice.service.LoginService
import io.reactivex.Single
import javax.inject.Inject

class AnalysisRepo @Inject constructor(private var analysisService: AnalysisService) {
    fun getAnalysisToday(analysisRequest: AnalysisRequest): Single<AnalysisResponse> {
        return analysisService.getAnalysisToday(analysisRequest)
    }
    fun getAnalysisWeek(analysisRequest: AnalysisRequest): Single<AnalysisResponse> {
        return analysisService.getAnalysisWeek(analysisRequest)
    }
}