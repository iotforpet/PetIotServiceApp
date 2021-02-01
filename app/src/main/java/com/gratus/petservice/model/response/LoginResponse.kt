package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import com.gratus.petservice.model.common.UserDetails
import java.io.Serializable

class LoginResponse : Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("Output")
    var message: String? = null
    @SerializedName("userDetails")
    var userDetails: UserDetails? = null

    constructor()

    constructor(status: Int, success: Boolean, message: String?) {
        this.status = status
        this.success = success
        this.message = message
    }

    constructor(status: Int, success: Boolean) {
        this.status = status
        this.success = success
    }

}