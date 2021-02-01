package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class GetServiceInfoRequest : BaseObservable {
    @SerializedName("service_catalog")
    var service_catalog: String? = null

    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("petID")
    var petID: String? = null
    @Inject
    constructor()
    constructor(service_catalog: String?, deviceId: String?, petID: String?) : super() {
        this.service_catalog = service_catalog
        this.deviceId = deviceId
        this.petID = petID
    }

}