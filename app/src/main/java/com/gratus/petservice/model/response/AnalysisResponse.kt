package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AnalysisResponse:Serializable {
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("Result")
    var success: Boolean = false
    @SerializedName("Output")
    var output: ArrayList<Graph> = ArrayList()
    constructor(status: Int,success: Boolean) {
        this.success = success
        this.status = status
    }

    constructor()
    constructor(status: Int, success: Boolean, output: ArrayList<Graph>) {
        this.status = status
        this.success = success
        this.output = output
    }
}