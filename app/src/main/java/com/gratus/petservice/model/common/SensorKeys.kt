package com.gratus.petservice.model.common

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SensorKeys:Serializable{
    @SerializedName("T-Sensors")
    private var T_Sensors: List<String>?=null
    @SerializedName("W-Sensors")
    private var W_Sensors: List<String>?=null
    @SerializedName("WL-Sensors")
    private var WL_Sensors: List<String>?=null

    constructor()
    constructor(T_Sensors: List<String>?, W_Sensors: List<String>?, WL_Sensors: List<String>?) {
        this.T_Sensors = T_Sensors
        this.W_Sensors = W_Sensors
        this.WL_Sensors = WL_Sensors
    }


}