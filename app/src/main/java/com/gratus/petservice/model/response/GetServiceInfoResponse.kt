package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import com.gratus.petservice.model.common.GetInfoOutput
import java.io.Serializable

class GetServiceInfoResponse : Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("Output")
    var getServiceInfo: GetServiceInfoOutput? = null

    constructor()

    constructor(status: Int, success: Boolean) {
        this.status = status
        this.success = success
    }

    constructor(status: Int, success: Boolean, getServiceInfo: GetServiceInfoOutput?) {
        this.status = status
        this.success = success
        this.getServiceInfo = getServiceInfo
    }
}