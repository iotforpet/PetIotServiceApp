package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StatusSensor : Serializable {
    @SerializedName("WL_1")
    var wl_1:Int  = 0
    @SerializedName("temp_1")
    var temp_1:Int  = 0
    @SerializedName("W_1")
    var w_1:Int  = 0
    @SerializedName("W_2")
    var w_2:Int  = 0

    constructor()
    constructor(wl_1: Int, temp_1: Int, w_1: Int, w_2: Int) {
        this.wl_1 = wl_1
        this.temp_1 = temp_1
        this.w_1 = w_1
        this.w_2 = w_2
    }


}