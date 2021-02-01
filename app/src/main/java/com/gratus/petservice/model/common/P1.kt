package com.gratus.petservice.model.common

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class P1 :Serializable{
    @SerializedName("DC")
    var DC: String? = null
    @SerializedName("SC")
    var SC: String? = null
    @SerializedName("Sensors")
    var Sensors: List<String>? = null

    constructor()
    constructor(DC: String?, SC: String?, Sensors: List<String>?) {
        this.DC = DC
        this.SC = SC
        this.Sensors = Sensors
    }


}