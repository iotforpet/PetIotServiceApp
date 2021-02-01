package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R


import javax.inject.Inject

class LoginRequest : BaseObservable {
    @SerializedName("username")
    var username: String? = null

    @SerializedName("password")
    var password: String? = null

    var usernameError: Int = R.string.username_none
    var passwordError: Int = R.string.password_none
    var usernameChange = false
    var passwordChange = false
    var visibilityProgressBar = false
    var visibilityButton = false

    @Inject
    constructor()
    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }


    fun isUsernameValid(): Boolean {
        return if (username != null) {
            if (username!!.length > 2) {
                usernameChange = false
                usernameError = R.string.username_none
                notifyChange()
                true
            } else {
                usernameChange = true
                usernameError = R.string.username_empty
                notifyChange()
                false
            }
        } else {
            usernameChange = true
            usernameError = R.string.username_empty
            notifyChange()
            false
        }
    }

    fun isPasswordLength(): Boolean {
        return if (password != null) {
            if (password!!.length > 2) {
                passwordChange = false
                passwordError = R.string.password_none
                notifyChange()
                true
            } else {
                passwordChange = true
                passwordError = R.string.password_empty
                notifyChange()
                false
            }
        } else {
            passwordChange = true
            passwordError = R.string.password_empty
            notifyChange()
            false
        }
    }

    @Bindable
    fun getUsernameTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Do nothing.
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                username = s.toString()
                isUsernameValid()
            }
        }
    }

    @Bindable
    fun getPasswordTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Do nothing.
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                password = s.toString()
                isPasswordLength()
            }
        }
    }

    fun getProgressVisibility(b: Boolean) {
        visibilityProgressBar = b
        notifyChange()
    }

    fun getButtonVisibility(b: Boolean) {
        visibilityButton = b
        notifyChange()
    }
}