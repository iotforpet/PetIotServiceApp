package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StatusCurrent : Serializable {
    @SerializedName("date")
    var date:String?  = null
    @SerializedName("sensorType")
    var sensorType:String?  = null
    @SerializedName("sensorID")
    var sensorID:String?  = null
    @SerializedName("sensorName")
    var sensorName:String?  = null
    @SerializedName("sensorData")
    var sensorData:Float  = 0.0F
    @SerializedName("unit")
    var unit:String?  = null
    @SerializedName("Status")
    var status:String?  = null

    constructor()
    constructor(date: String?, sensorType: String?, sensorID: String?, sensorName: String?, sensorData: Float, unit: String?, status: String?) {
        this.date = date
        this.sensorType = sensorType
        this.sensorID = sensorID
        this.sensorName = sensorName
        this.sensorData = sensorData
        this.unit = unit
        this.status = status
    }
}