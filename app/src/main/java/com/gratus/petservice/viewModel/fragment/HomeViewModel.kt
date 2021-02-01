package com.gratus.petservice.viewModel.fragment


import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.gratus.petservice.model.request.AppointRequest
import com.gratus.petservice.model.request.HomeRequest
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.HomeRespoonse
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.repository.AppointRepo
import com.gratus.petservice.repository.HomeRepo
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

class HomeViewModel @Inject constructor(
        private var homeRepo: HomeRepo,
) : BaseViewModel() {
    var homeRespoonse: MutableLiveData<HomeRespoonse> =
            MutableLiveData<HomeRespoonse>()
    var homeRequest: HomeRequest = HomeRequest()
    fun hitHome() {
        homeRequest.e_mail = getPrefs()?.getUsername()
        homeRequest.device_catalog = getPrefs()?.getDevice()
        homeRequest.service_catalog = getPrefs()?.getService()
        homeRequest.petID = getPrefs()?.getUserId()
        homeRequest.deviceId = getPrefs()?.getDeviceId()
        val gson = Gson()
        val json =getPrefs()!!.getSensorList()
        val dataList = gson.fromJson(json, Array<String>::class.java).asList()
        homeRequest.data = dataList
        if (isNetworkConnected()) {
                compositeDisposable.add(
                        homeRepo.setAppointResponse(homeRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<HomeRespoonse?>() {
                                    override fun onSuccess(homeRespoonses: HomeRespoonse) {
                                        homeRespoonse.value = homeRespoonses
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                homeRespoonse.value =
                                                        HomeRespoonse(
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
            homeRespoonse.value =
                    HomeRespoonse(
                            AppConstants.NETWORK_CODE_EXP,
                            false
                    )
        }
    }
}
