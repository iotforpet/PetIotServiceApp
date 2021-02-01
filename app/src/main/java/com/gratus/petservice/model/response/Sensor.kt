package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Sensor : Serializable {
    @SerializedName("Output")
    var output:StatusSensor  = StatusSensor()
}