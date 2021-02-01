package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DeviceStatusRespoonse:Serializable {
    @SerializedName("Output")
    var deviceStatus: DeviceStatus? = null
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false

    constructor(status: Int,success: Boolean) {
        this.success = success
        this.status = status
    }

    constructor()

}
