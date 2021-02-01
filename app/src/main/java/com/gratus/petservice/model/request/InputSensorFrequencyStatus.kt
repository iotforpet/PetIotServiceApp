package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class InputSensorFrequencyStatus: BaseObservable {
    @SerializedName("service")
    var service: String? = null
    @SerializedName("properties")
    var properties: PropertiesSensor? = null


    constructor() : super()
    constructor(service: String?, properties: PropertiesSensor?) : super() {
        this.service = service
        this.properties = properties
    }
}