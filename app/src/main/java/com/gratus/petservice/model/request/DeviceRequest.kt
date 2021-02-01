package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class DeviceRequest : BaseObservable {
    @SerializedName("device_catalog")
    var device_catalog: String? = null
    @SerializedName("petID")
    var petID: String? = null
    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("data")
    var data: List<String>? = null

    @Inject
    constructor()

    constructor(
        device_catalog: String?,
        petID: String?,
        deviceId: String?,
        data: List<String>?
    ) : super() {
        this.device_catalog = device_catalog
        this.petID = petID
        this.deviceId = deviceId
        this.data = data
    }

}