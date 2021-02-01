package com.gratus.petservice.model.common

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Devices:Serializable{
    @SerializedName("P1")
    var P1: P1?=null

    constructor(P1: P1?) {
        this.P1 = P1
    }

    constructor()
}