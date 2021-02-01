package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import com.gratus.petservice.model.common.GetInfoOutput
import java.io.Serializable

class GetInfoResponse : Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("Output")
    var getInfo: GetInfoOutput? = null

    constructor()

    constructor(status: Int, success: Boolean) {
        this.status = status
        this.success = success
    }

    constructor(status: Int, success: Boolean, getInfo: GetInfoOutput?) {
        this.status = status
        this.success = success
        this.getInfo = getInfo
    }
}