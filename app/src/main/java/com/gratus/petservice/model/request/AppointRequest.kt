package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R



import javax.inject.Inject

class AppointRequest : BaseObservable {
    @SerializedName("e_mail")
    var email: String? = null

    @SerializedName("typeOfService")
    var type_of_service: String? = null

    @SerializedName("customerVoice")
    var customer_voice: String? = null

    @SerializedName("timeStamp")
    var time_stamp: String? = null

    var usernameError: Int = R.string.username_none
    var passwordError: Int = R.string.password_none
    var usernameChange = false
    var passwordChange = false
    var visibilityProgressBar = false
    var visibilityButton = false

    @Inject
    constructor()

    constructor(email: String?, type_of_service: String?, customer_voice: String?, time_stamp: String?) : super() {
        this.email = email
        this.type_of_service = type_of_service
        this.customer_voice = customer_voice
        this.time_stamp = time_stamp
    }
}