package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DeviceStatus: Serializable {
    @SerializedName("WL_1")
    var water: Int? = null
    @SerializedName("temp_1")
    var temp: Int? = null
    @SerializedName("W_1")
    var food: Int? = null
    @SerializedName("W_2")
    var pet: Int? = null


    constructor(water: Int?, temp: Int?, food: Int?, pet: Int?) {
        this.water = water
        this.temp = temp
        this.food = food
        this.pet = pet
    }

    constructor()
}