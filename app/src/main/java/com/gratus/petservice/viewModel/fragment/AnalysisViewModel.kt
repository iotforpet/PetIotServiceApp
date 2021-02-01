package com.gratus.petservice.viewModel.fragment


import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.AnalysisRequest
import com.gratus.petservice.model.request.AppointRequest
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.AnalysisResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.repository.AnalysisRepo
import com.gratus.petservice.repository.AppointRepo
import com.gratus.petservice.repository.SignUpRepo
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.viewModel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(
        private var analysisRepo: AnalysisRepo,
) : BaseViewModel() {
    var analysisResponse: MutableLiveData<AnalysisResponse> =
            MutableLiveData<AnalysisResponse>()
    var analysisResponses: MutableLiveData<AnalysisResponse> =
        MutableLiveData<AnalysisResponse>()
    var analysisRequest: AnalysisRequest = AnalysisRequest()
    fun hitAnalysisToday(sensor:String) {
        analysisRequest.service_catalog = getPrefs()?.getService()
        analysisRequest.petID = getPrefs()?.getUserId()
        analysisRequest.deviceId = getPrefs()?.getDeviceId()
        analysisRequest.sensorId = sensor
        if (isNetworkConnected()) {
                compositeDisposable.add(
                        analysisRepo.getAnalysisToday(analysisRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<AnalysisResponse?>() {
                                    override fun onSuccess(analysisResponses: AnalysisResponse) {
                                        analysisResponse.value = analysisResponses
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                analysisResponse.value =
                                                        AnalysisResponse(
                                                                jObjError.getInt("status"),
                                                                false)
                                            } catch (ex: JSONException) {
                                                ex.printStackTrace()
                                            } catch (ex: IOException) {
                                                ex.printStackTrace()
                                            }
                                        }
                                    }
                                })
                )
        } else {
            analysisResponse.value =
                    AnalysisResponse(
                            AppConstants.NETWORK_CODE_EXP,
                            false
                    )
        }
    }
    fun hitAnalysisWeek(sensor:String) {
        analysisRequest.service_catalog = getPrefs()?.getService()
        analysisRequest.petID = getPrefs()?.getUserId()
        analysisRequest.deviceId = getPrefs()?.getDeviceId()
        analysisRequest.sensorId = sensor
        if (isNetworkConnected()) {
            compositeDisposable.add(
                    analysisRepo.getAnalysisWeek(analysisRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<AnalysisResponse?>() {
                                override fun onSuccess(analysisResponsess: AnalysisResponse) {
                                    analysisResponses.value = analysisResponsess
                                }

                                override fun onError(e: Throwable) {
                                    if (e is HttpException) {
                                        val response = e.response()
                                        try {
                                            val jObjError =
                                                    JSONObject(response!!.errorBody()!!.string())
                                            println(jObjError)
                                            analysisResponses.value =
                                                    AnalysisResponse(
                                                            jObjError.getInt("status"),
                                                            false)
                                        } catch (ex: JSONException) {
                                            ex.printStackTrace()
                                        } catch (ex: IOException) {
                                            ex.printStackTrace()
                                        }
                                    }
                                }
                            })
            )
        } else {
            analysisResponses.value =
                    AnalysisResponse(
                            AppConstants.NETWORK_CODE_EXP,
                            false
                    )
        }
    }
}
