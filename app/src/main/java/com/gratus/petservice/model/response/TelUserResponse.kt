package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import com.gratus.petservice.model.common.UserDetails
import java.io.Serializable

class TelUserResponse:Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("Output")
    var output: Tel_Users? = null
    constructor()
    constructor(status: Int, success: Boolean) {
        this.status = status
        this.success = success
    }
}