package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class InputDeviceStatus: BaseObservable {
    @SerializedName("sensor")
    var sensor: String? = null
    @SerializedName("sensorID")
    var sensorID: String? = null
    @SerializedName("properties")
    var properties: Properties? = null

    constructor(sensor: String?, sensorID: String?, properties: Properties?) : super() {
        this.sensor = sensor
        this.sensorID = sensorID
        this.properties = properties
    }

    constructor() : super()
}