package com.gratus.petservice.model.common

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GetInfoOutput: Serializable {
    @SerializedName("Devices")
    var Devices: Devices?=null
    @SerializedName("Mobile_Users")
    var Mobile_Users: List<Any>?=null
    @SerializedName("PetID")
    var PetID: String?=null
    @SerializedName("SensorKeys")
    var SensorKeys: SensorKeys?=null
    @SerializedName("Tel_Users")
    var Tel_Users: List<Int>?=null
    @SerializedName("lastUpdate")
    var lastUpdate: String?=null
    @SerializedName("serverURL")
    var serverURL: String?=null

    constructor()
    constructor(
        Devices: Devices?,
        Mobile_Users: List<Any>?,
        PetID: String?,
        SensorKeys: SensorKeys?,
        Tel_Users: List<Int>?,
        lastUpdate: String?,
        serverURL: String?
    ) {
        this.Devices = Devices
        this.Mobile_Users = Mobile_Users
        this.PetID = PetID
        this.SensorKeys = SensorKeys
        this.Tel_Users = Tel_Users
        this.lastUpdate = lastUpdate
        this.serverURL = serverURL
    }


}