package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class UserRequest:BaseObservable {
    @SerializedName("resource_catalog")
    var resource_catalog: String? = null
    @SerializedName("userID")
    var userID: String? = null
    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("e_mail")
    var e_mail: String? = null
    @SerializedName("type")
    var type: String? = null



    constructor() : super()



    constructor(type: String?) : super() {
        this.type = type
    }

    constructor(resource_catalog: String?, userID: String?, e_mail: String?) : super() {
        this.resource_catalog = resource_catalog
        this.userID = userID
        this.e_mail = e_mail
    }

    constructor(
        resource_catalog: String?,
        userID: String?,
        deviceId: String?,
        e_mail: String?
    ) : super() {
        this.resource_catalog = resource_catalog
        this.userID = userID
        this.deviceId = deviceId
        this.e_mail = e_mail
    }


}