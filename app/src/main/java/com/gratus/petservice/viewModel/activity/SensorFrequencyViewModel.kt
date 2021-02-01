package com.gratus.petservice.viewModel.activity


import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.*
import com.gratus.petservice.model.response.GetServiceInfoResponse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.repository.GetServiceInfoRepo
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

class SensorFrequencyViewModel @Inject constructor(
        private var getServiceInfoRepo: GetServiceInfoRepo,
) : BaseViewModel() {
    var getServiceInfoResponseMutableLiveData: MutableLiveData<GetServiceInfoResponse> =
        MutableLiveData<GetServiceInfoResponse>()
    var getServiceInfoRequest: GetServiceInfoRequest = GetServiceInfoRequest()
    var updateSensorFrequencyRequest: UpdateSensorFrequencyRequest = UpdateSensorFrequencyRequest()
    var profileResponseMutableLiveData: MutableLiveData<ProfileResponse> =
        MutableLiveData<ProfileResponse>()

    fun hitSensorStatus() {
        if (isNetworkConnected()) {
            getServiceInfoRequest.service_catalog=(getPrefs()!!.getService())
            getServiceInfoRequest.deviceId=(getPrefs()!!.getDeviceId())
            getServiceInfoRequest.petID=(getPrefs()!!.getUserId())
                compositeDisposable.add(
                    getServiceInfoRepo.getServiceInfoResponse(getServiceInfoRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<GetServiceInfoResponse?>() {
                                    override fun onSuccess(getServiceInfoResponse: GetServiceInfoResponse) {
                                        getServiceInfoResponseMutableLiveData.value = getServiceInfoResponse
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                getServiceInfoResponseMutableLiveData.value =
                                                    GetServiceInfoResponse(
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
            getServiceInfoResponseMutableLiveData.value =
                GetServiceInfoResponse(
                        AppConstants.NETWORK_CODE_EXP,
                        false
                )
        }
    }

    fun hitUpdateSensorStatus(sensor: ArrayList<String>, freq: ArrayList<Int>) {
        if (isNetworkConnected()) {
            updateSensorFrequencyRequest.service_catalog = (getPrefs()!!.getService())
            updateSensorFrequencyRequest.petID = (getPrefs()!!.getUserId())
            updateSensorFrequencyRequest.deviceId = (getPrefs()!!.getDeviceId())
            for (i in 0..freq.size-1) {
                var properties: PropertiesSensor = PropertiesSensor(freq[i])
                var inputSensorFrequencyStatus: InputSensorFrequencyStatus =
                    InputSensorFrequencyStatus(sensor[i], properties)
                updateSensorFrequencyRequest.data.add(inputSensorFrequencyStatus)
            }
            compositeDisposable.add(
                getServiceInfoRepo.setUpdateInfoResponse(updateSensorFrequencyRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                        override fun onSuccess(profileResponse: ProfileResponse) {
                            profileResponseMutableLiveData.value = profileResponse
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    profileResponseMutableLiveData.value =
                                        ProfileResponse(
                                            jObjError.getInt("status"),
                                            false, jObjError.getString("message")
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
            profileResponseMutableLiveData.value =
                ProfileResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }
}
