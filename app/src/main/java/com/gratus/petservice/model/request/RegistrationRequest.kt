package com.gratus.petservice.model.request

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import com.gratus.petservice.R
import java.io.Serializable

class RegistrationRequest: BaseObservable, Serializable {
    @SerializedName("username")
     var username: String? = null
    @SerializedName("e_mail")
     var e_mail: String? = null
    @SerializedName("password")
     var password: String? = null
     var confirmpassword: String? = null
    @SerializedName("userID")
     var userID: String? = null
     var type: String? = null
    @SerializedName("name_of_animal")
    var name_of_animal: String? = null
    @SerializedName("phone_number")
    var phone: String? = null
    @SerializedName("type_of_animal")
    var type_of_animal: String? = null
    @SerializedName("breed")
    var breed: String? = null
    @SerializedName("age_of_animal")
    var age_of_animal: String? = null
    @SerializedName("sex_of_animal")
    var sex_of_animal: String? = null
    @get:JsonIgnore
     var usernameError: Int? = null
    @JsonIgnore
     var e_mailError: Int? = null
    @JsonIgnore
     var passwordError: Int? = null
    @JsonIgnore
     var confirmpasswordError: Int? = null
    @JsonIgnore
     var userIDError: Int? = null
    var phoneError: Int? = null
    var ageError: String? = null
    var breedError: String? = null
    var typeError: String? = null
    var nameaError: String? = null

    private val usernameTextWatcher: TextWatcher? = null
    private val e_mailTextWatcher: TextWatcher? = null
    private val passwordTextWatcher: TextWatcher? = null
    private val confirmPasswordTextWatcher: TextWatcher? = null
    private var userIDTextWatcher: String? = null
    private val phoneTextWatcher: TextWatcher? = null
    private var ageTextWatcher: String? = null
    private var typeWatcherr: String? = null
    private var breedTextWatcher: String? = null
    private var nameaTextWatcher: String? = null

    @JsonIgnore
     var usernameChange:Boolean = false
    @JsonIgnore
     var e_mailEnable:Boolean = false
    @JsonIgnore
     var e_mailChange:Boolean = false
    @JsonIgnore
     var passwordChange:Boolean = false
    @JsonIgnore
     var confirmPasswordChange = false
    @JsonIgnore
     var confirmPasswordEnable = false
    @JsonIgnore
     var userIDChange:Boolean = false
    var phoneChange:Boolean = false
    var ageChange:Boolean = false
    var breedChange:Boolean = false
    var typeChange:Boolean = false
    var nameaChange:Boolean = false
    @JsonIgnore
     var visibilityProgressBar:Boolean = false
    @JsonIgnore
     var visibilityButton:Boolean = false
    
    constructor() : super()
    
    constructor(
        userusername: String?,
        e_mail: String?,
        password: String?,
        userID: String?,
        type: String?
    ) : super() {
        this.username = userusername
        this.e_mail = e_mail
        this.password = password
        this.userID = userID
        this.type = type
    }

    constructor(
        e_mail: String?,
        name_of_animal: String?,
        phone: String?,
        type_of_animal: String?,
        breed: String?,
        age_of_animal: String?,
        sex_of_animal: String?
    ) : super() {
        this.e_mail = e_mail
        this.name_of_animal = name_of_animal
        this.phone = phone
        this.type_of_animal = type_of_animal
        this.breed = breed
        this.age_of_animal = age_of_animal
        this.sex_of_animal = sex_of_animal
    }

    fun isEmailValid(): Boolean {
        return if (e_mail != null) {
            if (Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()) {
                e_mailChange = false
                e_mailError = (R.string.email_none)
                notifyChange()
                return true
            } else {
                e_mailChange = true
                e_mailError = (R.string.email_empty)
                notifyChange()
                return false
            }
        } else {
            e_mailChange = true
            e_mailError = (R.string.email_empty)
            notifyChange()
            return false
        }
    }

    fun isNameValid(): Boolean {
        return if (username != null) {
            if (username!!.length > 3) {
                usernameChange = false
                usernameError = (R.string.username_none)
                notifyChange()
                return true
            } else {
                usernameChange = true
                usernameError = (R.string.username_empty)
                notifyChange()
                return false
            }
        } else {
            usernameChange = true
            usernameError = (R.string.username_empty)
            notifyChange()
            return false
        }
    }

    fun isPasswordLengthGreaterThan7(): Boolean {
        return if (password != null) {
            if (password!!.length > 7) {
                passwordChange=false
                passwordError=R.string.password_none
                confirmPasswordEnable=true
                notifyChange()
                true
            } else {
                passwordChange=true
                passwordError=R.string.password_empty
                confirmPasswordEnable=false
                notifyChange()
                false
            }
        } else {
            passwordChange=true
            passwordError=R.string.password_empty
            confirmPasswordEnable=false
            notifyChange()
            false
        }
    }

    fun isConfirmPasswordLengthGreaterThan7(): Boolean {
        return if (confirmpassword != null) {
            if (confirmpassword == password) {
                if (confirmpassword!!.length > 7) {
                    confirmPasswordChange  = false
                    confirmpasswordError  = R.string.password_none
                    notifyChange()
                    true
                } else {
                    confirmPasswordChange =  true
                    confirmpasswordError  = R.string.password_empty
                    notifyChange()
                    false
                }
            } else {
                confirmPasswordChange =  true
                confirmpasswordError  =  R.string.password_match
                notifyChange()
                false
            }
        } else {
            confirmPasswordChange = true
            confirmpasswordError = R.string.password_empty
            notifyChange()
            false
        }
    }

    fun isUserValid(): Boolean {
        return if (userID != null) {
            if (userID!!.length >2) {
                userIDChange = false
                userIDError = R.string.userId_none
                notifyChange()
                return true
            } else {
                userIDChange = true
                userIDError = R.string.userId_empty
                notifyChange()
                return false
            }
        } else {
            userIDChange = true
            userIDError = R.string.userId_empty
            notifyChange()
            return false
        }
    }
    fun isPhoneValid(): Boolean {
        return if (phone != null) {
            if (phone!!.length == 10) {
                phoneChange = false
                phoneError = (R.string.phone_none)
                notifyChange()
                return true
            } else {
                phoneChange = true
                phoneError = (R.string.phone_empty)
                notifyChange()
                return false
            }
        } else {
            phoneChange = true
            phoneError = (R.string.phone_empty)
            notifyChange()
            return false
        }
    }

    fun isAgeValid(): Boolean {
        return if (age_of_animal != null) {
            if (age_of_animal!!.length > 0) {
                ageChange = false
                ageError =""
                notifyChange()
                return true
            } else {
                ageChange = true
                ageError = "Please enter age "
                notifyChange()
                return false
            }
        } else {
            ageChange = true
            ageError =  "Please enter age "
            notifyChange()
            return false
        }
    }

    fun isTypeValid(): Boolean {
        return if (type_of_animal != null) {
            if (type_of_animal!!.length > 2) {
                typeChange = false
                typeError =""
                notifyChange()
                return true
            } else {
                typeChange = true
                typeError = "Please enter type of animal"
                notifyChange()
                return false
            }
        } else {
            typeChange = true
            typeError = "Please enter type of animal"
            notifyChange()
            return false
        }
    }

    fun isBreedValid(): Boolean {
        return if (breed != null) {
            if (breed!!.length > 2) {
                breedChange = false
                breedError =""
                notifyChange()
                return true
            } else {
                breedChange = true
                breedError = "Please enter type of breed"
                notifyChange()
                return false
            }
        } else {
            breedChange = true
            breedError = "Please enter type of breed"
            notifyChange()
            return false
        }
    }

    fun isNameaValid(): Boolean {
        return if (name_of_animal != null) {
            if (name_of_animal!!.length > 2) {
                nameaChange = false
                nameaError =""
                notifyChange()
                return true
            } else {
                nameaChange = true
                nameaError = "Please enter name of animal"
                notifyChange()
                return false
            }
        } else {
            nameaChange = true
            nameaError = "Please enter name of animal"
            notifyChange()
            return false
        }
    }
    @Bindable
    fun getNameTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                username = (s.toString())
                isNameValid()
            }
        }
    }


    @Bindable
    fun getEmailTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                e_mail = (s.toString())
                isEmailValid()
            }
        }
    }


    @Bindable
    fun getPasswordTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                password = s.toString()
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
                confirmpassword = s.toString()
                isConfirmPasswordLengthGreaterThan7()
            }
        }
    }


    @Bindable
    fun getPhoneTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                phone = s.toString()
                isPhoneValid()
            }
        }
    }

    @Bindable
    fun getUserIDTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                userID = (s.toString())
                isUserValid()
            }
        }
    }

    @Bindable
    fun getTypeTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                type_of_animal = (s.toString())
                isTypeValid()
            }
        }
    }

    fun getBreedTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                breed = (s.toString())
                isBreedValid()
            }
        }
    }

    fun getAgeTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                age_of_animal = (s.toString())
                isAgeValid()
            }
        }
    }

    fun getNameaTextWatcher(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                name_of_animal = (s.toString())
                isNameaValid()
            }
        }
    }
    
    fun getProgressVisibility(b: Boolean) {
        visibilityProgressBar = b
        notifyChange()
    }

    fun getEmailEnable(b: Boolean) {
        e_mailEnable = b
        notifyChange()
    }


    fun getButtonVisibility(b: Boolean) {
        visibilityButton = b
        notifyChange()
    }
}