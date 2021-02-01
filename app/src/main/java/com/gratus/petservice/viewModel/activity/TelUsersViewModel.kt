package com.gratus.petservice.viewModel.activity


import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.TelUserResponse
import com.gratus.petservice.repository.*
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

class TelUsersViewModel @Inject constructor(
        private var telUserRepo: TelUserRepo,
) : BaseViewModel() {
    var profileResponse: MutableLiveData<ProfileResponse> =
            MutableLiveData<ProfileResponse>()
    var telUserResponse: MutableLiveData<TelUserResponse> =
        MutableLiveData<TelUserResponse>()
    var telUserRequest: TelUserRequest = TelUserRequest()
    fun hitTelUser() {
        telUserRequest.resource_catalog = getPrefs()?.getResource()
        telUserRequest.petID = getPrefs()?.getUserId()
        if (isNetworkConnected()) {
                compositeDisposable.add(
                    telUserRepo.getTelUsersResponse(telUserRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<TelUserResponse?>() {
                                    override fun onSuccess(telUserResponses: TelUserResponse) {
                                        telUserResponse.value = telUserResponses
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                telUserResponse.value =
                                                    TelUserResponse(
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
            telUserResponse.value =
                TelUserResponse(
                            AppConstants.NETWORK_CODE_EXP,
                            false
                    )
        }
    }

    fun hitTelDelteUser(position: Int) {
        telUserRequest.resource_catalog = getPrefs()?.getResource()
        telUserRequest.petID = getPrefs()?.getUserId()
        var telUser: ArrayList<Int> = ArrayList()
        telUser.add(position)
        telUserRequest.Users = telUser
        if (isNetworkConnected()) {
            compositeDisposable.add(
                telUserRepo.getDeleteTelResponse(telUserRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                        override fun onSuccess(profileResponses: ProfileResponse) {
                            profileResponse.value = profileResponses
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    profileResponse.value =
                                        ProfileResponse(
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
            profileResponse.value =
                ProfileResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }

    fun hitTelAddteUser(position: Int) {
        telUserRequest.resource_catalog = getPrefs()?.getResource()
        telUserRequest.petID = getPrefs()?.getUserId()
        var telUser: ArrayList<Int> = ArrayList()
        telUser.add(position)
        telUserRequest.Users = telUser
        if (isNetworkConnected()) {
            compositeDisposable.add(
                telUserRepo.getAddTelResponse(telUserRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                        override fun onSuccess(profileResponses: ProfileResponse) {
                            profileResponse.value = profileResponses
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    profileResponse.value =
                                        ProfileResponse(
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
            profileResponse.value =
                ProfileResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }
}
