package com.gratus.petservice.viewModel.activity


import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.gratus.petservice.model.request.DeviceRequest
import com.gratus.petservice.model.request.InputDeviceStatus
import com.gratus.petservice.model.request.Properties
import com.gratus.petservice.model.request.UpdateDeviceRequest
import com.gratus.petservice.model.response.DeviceStatusRespoonse
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.repository.DeviceRepo
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

class SensorActiveDeactiveViewModel @Inject constructor(
        private var deviceRepo: DeviceRepo,
) : BaseViewModel() {
    var deviceStatusResponseMutableLiveData: MutableLiveData<DeviceStatusRespoonse> =
        MutableLiveData<DeviceStatusRespoonse>()
    var deviceRequest: DeviceRequest = DeviceRequest()
    var updateDeviceRequest: UpdateDeviceRequest = UpdateDeviceRequest()
    var profileResponseMutableLiveData: MutableLiveData<ProfileResponse> =
        MutableLiveData<ProfileResponse>()

    fun hitDeviceStatus() {
        if (isNetworkConnected()) {
            deviceRequest.deviceId=(getPrefs()!!.getDeviceId())
            deviceRequest.device_catalog=(getPrefs()!!.getDevice())
            deviceRequest.petID=(getPrefs()!!.getUserId())
            val gson = Gson()
            val json =getPrefs()!!.getSensorList()
            val dataList = gson.fromJson(json, Array<String>::class.java).asList()
            deviceRequest.data=dataList
                compositeDisposable.add(
                    deviceRepo.getDeviceStatus(deviceRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<DeviceStatusRespoonse?>() {
                                    override fun onSuccess(deviceStatusRespoonse: DeviceStatusRespoonse) {
                                        deviceStatusResponseMutableLiveData.value = deviceStatusRespoonse
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                deviceStatusResponseMutableLiveData.value =
                                                    DeviceStatusRespoonse(
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
            deviceStatusResponseMutableLiveData.value =
                DeviceStatusRespoonse(
                        AppConstants.NETWORK_CODE_EXP,
                        false
                )
        }
    }

    fun hitUpdateDeviceStatus(sensorId: String, sensor: String, checked: Boolean) {
        if (isNetworkConnected()) {
            updateDeviceRequest.device_catalog = (getPrefs()!!.getDevice())
            updateDeviceRequest.petID = (getPrefs()!!.getUserId())
            updateDeviceRequest.deviceId = (getPrefs()!!.getDeviceId())
            updateDeviceRequest.catalogURL = (getPrefs()!!.getResource())
            if(checked) {
                var properties:Properties = Properties(1)
                var inputDeviceStatus:InputDeviceStatus= InputDeviceStatus(sensor,sensorId,properties)
                updateDeviceRequest.data = inputDeviceStatus
            }
            else{
                var properties:Properties = Properties(0)
                var inputDeviceStatus:InputDeviceStatus= InputDeviceStatus(sensor,sensorId,properties)
                updateDeviceRequest.data = inputDeviceStatus
            }
            compositeDisposable.add(
                deviceRepo.setUpdateDeviceStatus(updateDeviceRequest)
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
