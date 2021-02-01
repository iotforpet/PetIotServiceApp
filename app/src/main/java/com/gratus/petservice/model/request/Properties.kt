package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class Properties: BaseObservable {
    @SerializedName("web_active")
    var web_active: Int = 0
    constructor() : super()
    constructor(web_active: Int) : super() {
        this.web_active = web_active
    }
}