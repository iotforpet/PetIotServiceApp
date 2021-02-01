package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class GetInfoRequest : BaseObservable {
    @SerializedName("resource_catalog")
    var resource_catalog: String? = null

    @SerializedName("deviceId")
    var deviceId: String? = null

    @Inject
    constructor()
    constructor(resource_catalog: String?, deviceId: String?) {
        this.resource_catalog = resource_catalog
        this.deviceId = deviceId
    }
}