package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Appoint:Serializable {
    @SerializedName("e_mail")
    var e_mail:String?  = null
    @SerializedName("typeOfService")
    var typeOfService:String?  = null
    @SerializedName("customerVoice")
    var customerVoice:String?  = null
    @SerializedName("timeStamp")
    var timeStamp:String?  = null

    constructor()
    constructor(e_mail: String?, typeOfService: String?, customerVoice: String?, timeStamp: String?) {
        this.e_mail = e_mail
        this.typeOfService = typeOfService
        this.customerVoice = customerVoice
        this.timeStamp = timeStamp
    }
}