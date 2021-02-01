package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Users:Serializable {
    @SerializedName("e_mail")
    var e_mail: String? = null
    @SerializedName("username")
    var name: String? = null
    @SerializedName("IDS")
    var ids: ArrayList<PetDetails>? = null
    constructor()
}