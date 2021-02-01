package com.gratus.petservice.viewModel.activity

import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.GetInfoRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.repository.GetInfoRepo
import com.gratus.petservice.repository.LoginRepo
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.viewModel.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private var loginRepo: LoginRepo,
    private var getInfoRepo: GetInfoRepo
) :
    BaseViewModel() {
    val loginResponseMutableLiveData: MutableLiveData<LoginResponse> =
        MutableLiveData<LoginResponse>()
    var getInfoResponse: MutableLiveData<GetInfoResponse> =
        MutableLiveData<GetInfoResponse>()
    var getInfoRequest: GetInfoRequest = GetInfoRequest()
    fun hitLogin() {
        val loginRequest = LoginRequest(getPrefs()?.getUsername(), getPrefs()?.getPassword())
        if (isNetworkConnected()) {
                compositeDisposable.add(
                    loginRepo.getLoginResponse(loginRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<LoginResponse?>() {
                            override fun onSuccess(loginResponse: LoginResponse) {
                                loginResponseMutableLiveData.setValue(loginResponse)
                            }

                            override fun onError(e: Throwable) {
                                if (e is HttpException) {
                                    val response = e.response()
                                    try {
                                        val jObjError =
                                            JSONObject(response!!.errorBody()!!.string())
                                        println(jObjError)
                                        loginResponseMutableLiveData.setValue(
                                            LoginResponse(
                                                jObjError.getInt("status"),
                                                false, jObjError.getString("Output")
                                            )
                                        )
                                    } catch (ex: JSONException) {
                                        ex.printStackTrace()
                                    } catch (ex: IOException) {
                                        ex.printStackTrace()
                                    }
                                }
                            }
                        })
                )
        }  else {
            loginResponseMutableLiveData.value =
                LoginResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }

    fun hitGetInfo() {
        if (isNetworkConnected()) {
            getInfoRequest.resource_catalog = getPrefs()?.getResource()
            getInfoRequest.deviceId = getPrefs()?.getDeviceId()
            compositeDisposable.add(
                getInfoRepo.getLoginResponse(getInfoRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<GetInfoResponse?>() {
                        override fun onSuccess(getInfoResponses: GetInfoResponse) {
                            getInfoResponse.value = getInfoResponses
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    getInfoResponse.value =
                                        GetInfoResponse(
                                            jObjError.getInt("status"),
                                            false
                                        )
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
            getInfoResponse.value =
                GetInfoResponse(
                    NETWORK_CODE_EXP,
                    false
                )
        }
    }
}