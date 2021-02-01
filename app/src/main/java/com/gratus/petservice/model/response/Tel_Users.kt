package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tel_Users:Serializable {
    @SerializedName("Tel_Users")
    var telUsers: ArrayList<Int>? = null
    constructor()
}