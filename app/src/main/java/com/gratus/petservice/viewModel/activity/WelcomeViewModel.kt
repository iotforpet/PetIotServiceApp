package com.gratus.petservice.viewModel.activity


import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.GetInfoRequest
import com.gratus.petservice.model.request.LoginRequest
import com.gratus.petservice.model.response.GetInfoResponse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.repository.GetInfoRepo
import com.gratus.petservice.repository.LoginRepo
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

class WelcomeViewModel @Inject constructor(
    private var loginRepo: LoginRepo,
    private var getInfoRepo: GetInfoRepo
) : BaseViewModel() {
    var loginResponses: MutableLiveData<LoginResponse> =
        MutableLiveData<LoginResponse>()
    var loginRequest: LoginRequest = LoginRequest()
    var getInfoResponse: MutableLiveData<GetInfoResponse> =
        MutableLiveData<GetInfoResponse>()
    var getInfoRequest: GetInfoRequest = GetInfoRequest()
    fun hitLogin() {
        if (isNetworkConnected()) {
            if (loginRequest.isUsernameValid() && loginRequest.isPasswordLength()) {
                loginRequest.getButtonVisibility(false)
                getDelay(true)
                compositeDisposable.add(
                    loginRepo.getLoginResponse(loginRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<LoginResponse?>() {
                            override fun onSuccess(loginResponse: LoginResponse) {
                                loginResponses.value = loginResponse
                            }

                            override fun onError(e: Throwable) {
                                if (e is HttpException) {
                                    val response = e.response()
                                    try {
                                        val jObjError =
                                            JSONObject(response!!.errorBody()!!.string())
                                        println(jObjError)
                                        loginResponses.value =
                                            LoginResponse(
                                                jObjError.getInt("status"),
                                                false, jObjError.getString("Output")
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
            }
        } else {
            loginResponses.value =
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
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }

    fun getDelay(b: Boolean) {
        val handler = Handler()
        handler.postDelayed({ loginRequest.getProgressVisibility(b) }, 100)
    }
}
