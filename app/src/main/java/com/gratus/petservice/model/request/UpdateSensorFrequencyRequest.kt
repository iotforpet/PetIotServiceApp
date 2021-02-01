package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class UpdateSensorFrequencyRequest : BaseObservable {
    @SerializedName("service_catalog")
    var service_catalog: String? = null
    @SerializedName("petID")
    var petID: String? = null
    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("data")
    var data: ArrayList<InputSensorFrequencyStatus> = ArrayList()

    @Inject
    constructor()
    constructor(
        service_catalog: String?,
        petID: String?,
        deviceId: String?,
        data: ArrayList<InputSensorFrequencyStatus>
    ) : super() {
        this.service_catalog = service_catalog
        this.petID = petID
        this.deviceId = deviceId
        this.data = data
    }


}