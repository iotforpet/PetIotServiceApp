package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Graph:Serializable {
    @SerializedName("datetime")
    var datetime: String? = null
    @SerializedName("data")
    var data: Int? = null

    constructor(datetime: String?, data: Int?) {
        this.datetime = datetime
        this.data = data
    }

    constructor()


}