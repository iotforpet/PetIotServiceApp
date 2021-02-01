package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GetServiceInfoOutput:Serializable {
    @SerializedName("T_Sensors")
    var T_Sensors: Int=0
    @SerializedName("WL_Sensors")
    var WL_Sensors: Int=0
    @SerializedName("W_Sensors")
    var W_Sensors: Int=0

    constructor(T_Sensors: Int, WL_Sensors: Int, W_Sensors: Int) {
        this.T_Sensors = T_Sensors
        this.WL_Sensors = WL_Sensors
        this.W_Sensors = W_Sensors
    }

    constructor()
}