package com.gratus.petservice.model.request

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class TelUserRequest:BaseObservable {
    @SerializedName("resource_catalog")
    var resource_catalog: String? = null
    @SerializedName("petID")
    var petID: String? = null
    @SerializedName("Users")
    var Users: ArrayList<Int>? = null

    constructor(resource_catalog: String?, petID: String?, Users: ArrayList<Int>?) : super() {
        this.resource_catalog = resource_catalog
        this.petID = petID
        this.Users = Users
    }

    constructor() : super()
    constructor(resource_catalog: String?, petID: String?) : super() {
        this.resource_catalog = resource_catalog
        this.petID = petID
    }
}