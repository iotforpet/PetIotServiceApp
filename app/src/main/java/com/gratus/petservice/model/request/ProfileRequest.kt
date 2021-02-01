package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class ProfileRequest : BaseObservable {
    @SerializedName("e_mail")
    var username: String? = null
    @SerializedName("userID")
    var userID: String? = null
    @Inject
    constructor()
    constructor(username: String?) {
        this.username = username
    }

}