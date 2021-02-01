package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Current : Serializable {
    @SerializedName("Output")
    var output:ArrayList<StatusCurrent>  = ArrayList()
}