package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R
import javax.inject.Inject

class ResetPasswordRequest: BaseObservable {
    @SerializedName("e_mail")
    private var username: String? = null
    @SerializedName("password")
    private var password: String? = null
    @SerializedName("confirm_password")
    private var confirmPassword: String? = null
    private var usernameError: Int? = null
    private var newPasswordError: Int? = null
    private var confirmPasswordError: Int? = null
    private var usernameTextWatcher: TextWatcher? = null
    private var newPasswordTextWatcher: TextWatcher? = null
    private var confirmPasswordTextWatcher: TextWatcher? = null
    private var usernameChange:Boolean = false
    private var newPasswordChange:Boolean= false
    private var confirmPasswordChange:Boolean = false
    private var confirmPasswordEnable:Boolean = false
    private var visibilityProgressBar:Boolean = false
    private var visibilityButton:Boolean = false

    @Inject
    constructor() {
    }


   constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getConfirmPassword(): String? {
        return confirmPassword
    }

    fun setConfirmPassword(confirmPassword: String?) {
        this.confirmPassword = confirmPassword
    }

    fun getUsernameError(): Int? {
        return usernameError
    }

    fun setUsernameError(usernameError: Int?) {
        this.usernameError = usernameError
    }

    fun getNewPasswordError(): Int? {
        return newPasswordError
    }

    fun setNewPasswordError(newPasswordError: Int?) {
        this.newPasswordError = newPasswordError
    }

    fun getConfirmPasswordError(): Int? {
        return confirmPasswordError
    }

    fun setConfirmPasswordError(confirmPasswordError: Int?) {
        this.confirmPasswordError = confirmPasswordError
    }

    fun isUsernameChange(): Boolean {
        return usernameChange
    }

    fun setUsernameChange(usernameChange: Boolean) {
        this.usernameChange = usernameChange
    }

    fun isNewPasswordChange(): Boolean {
        return newPasswordChange
    }

    fun setNewPasswordChange(newPasswordChange: Boolean) {
        this.newPasswordChange = newPasswordChange
    }

    fun isConfirmPasswordChange(): Boolean {
        return confirmPasswordChange
    }

    fun setConfirmPasswordChange(confirmPasswordChange: Boolean) {
        this.confirmPasswordChange = confirmPasswordChange
    }

    fun isConfirmPasswordEnable(): Boolean {
        return confirmPasswordEnable
    }

    fun setConfirmPasswordEnable(confirmPasswordEnable: Boolean) {
        this.confirmPasswordEnable = confirmPasswordEnable
    }

    fun isVisibilityProgressBar(): Boolean {
        return visibilityProgressBar
    }

    fun setVisibilityProgressBar(visibilityProgressBar: Boolean) {
        this.visibilityProgressBar = visibilityProgressBar
    }

    fun isVisibilityButton(): Boolean {
        return visibilityButton
    }

    fun setVisibilityButton(visibilityButton: Boolean) {
        this.visibilityButton = visibilityButton
    }

    fun isEmailValid(): Boolean {
        return if (getUsername() != null) {
            if (Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches()) {
                setUsernameChange(false)
                setUsernameError(R.string.email_none)
                notifyChange()
                true
            } else {
                setUsernameChange(true)
                setUsernameError(R.string.email_empty)
                notifyChange()
                false
            }
        } else {
            setUsernameChange(true)
            setUsernameError(R.string.email_empty)
            notifyChange()
            false
        }
    }

    fun isPasswordLengthGreaterThan7(): Boolean {
        return if (getPassword() != null) {
            if (getPassword()!!.length > 7) {
                setNewPasswordChange(false)
                setNewPasswordError(R.string.password_none)
                setConfirmPasswordEnable(true)
                notifyChange()
                true
            } else {
                setNewPasswordChange(true)
                setNewPasswordError(R.string.password_empty)
                setConfirmPasswordEnable(false)
                notifyChange()
                false
            }
        } else {
            setNewPasswordChange(true)
            setNewPasswordError(R.string.password_empty)
            setConfirmPasswordEnable(false)
            notifyChange()
            false
        }
    }

    fun isConfirmPasswordLengthGreaterThan7(): Boolean {
        return if (getConfirmPassword() != null) {
            if (getConfirmPassword() == getPassword()) {
                if (getConfirmPassword()!!.length > 7) {
                    setConfirmPasswordChange(false)
                    setConfirmPasswordError(R.string.password_none)
                    notifyChange()
                    true
                } else {
                    setConfirmPasswordChange(true)
                    setConfirmPasswordError(R.string.password_empty)
                    notifyChange()
                    false
                }
            } else {
                setConfirmPasswordChange(true)
                setConfirmPasswordError(R.string.password_match)
                notifyChange()
                false
            }
        } else {
            setConfirmPasswordChange(true)
            setConfirmPasswordError(R.string.password_empty)
            notifyChange()
            false
        }
    }

    @Bindable
    fun getUsernameTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                setUsername(s.toString())
                isEmailValid()
            }
        }
    }

    @Bindable
    fun getNewPasswordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                setPassword(s.toString())
                isPasswordLengthGreaterThan7()
            }
        }
    }

    @Bindable
    fun getConfirmPasswordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                setConfirmPassword(s.toString())
                isConfirmPasswordLengthGreaterThan7()
            }
        }
    }

    fun getProgressVisibility(b: Boolean) {
        setVisibilityProgressBar(b)
        notifyChange()
    }

    fun getButtonVisibility(b: Boolean) {
        setVisibilityButton(b)
        notifyChange()
    }
}