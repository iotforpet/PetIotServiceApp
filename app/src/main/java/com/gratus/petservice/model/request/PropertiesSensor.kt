package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class PropertiesSensor: BaseObservable {
    @SerializedName("Frequency")
    var frequency: Int = 0
    constructor() : super()
    constructor(frequency: Int) : super() {
        this.frequency = frequency
    }
}