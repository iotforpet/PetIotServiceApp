package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HomeRespoonse:Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("SensorStatus")
    var sensorStatus: Sensor = Sensor()
    @SerializedName("UserSensorStatus")
    var userSensorStatus: Sensor = Sensor()
    @SerializedName("Current")
    var current: Current = Current()
    @SerializedName("appoint")
    var appoint: ArrayList<Appoint> = ArrayList()
    constructor(status: Int,success: Boolean) {
        this.success = success
        this.status = status
    }

    constructor()
    constructor(status: Int, success: Boolean, sensorStatus: Sensor, userSensorStatus: Sensor, current: Current, appoint: ArrayList<Appoint>) {
        this.status = status
        this.success = success
        this.sensorStatus = sensorStatus
        this.userSensorStatus = userSensorStatus
        this.current = current
        this.appoint = appoint
    }
}
