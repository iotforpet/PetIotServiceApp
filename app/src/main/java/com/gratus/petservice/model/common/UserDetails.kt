package com.gratus.petservice.model.common

import com.google.gson.annotations.SerializedName
import com.gratus.petservice.model.response.IDS
import com.gratus.petservice.model.response.PetDetails
import java.io.Serializable

class UserDetails:Serializable {
    @SerializedName("username")
    var username: String? = null
    @SerializedName("e_mail")
    var e_mail: String? = null
    @SerializedName("phone_number")
    var phone_number: String? = null
    @SerializedName("IDS")
    var info:ArrayList<PetDetails> = ArrayList()
    @SerializedName("type")
    var type: String? = null
//    @SerializedName("type_of_animal")
//    var type_of_animal: String? = null
//    @SerializedName("breed")
//    var breed: String? = null
//    @SerializedName("age_of_animal")
//    var age_of_animal: String? = null
//    @SerializedName("sex_of_animal")
//    var sex_of_animal: String? = null
//    @SerializedName("userID")
//    var userID: String? = null
//    @SerializedName("type")
//    var type: String? = null
//    @SerializedName("resource_catalog")
//    var resource_catalog: String? = null
//    @SerializedName("deviceId")
//    var deviceId: String? = null
//    @SerializedName("name_of_animal")
//    var name_of_animal: String? = null
    constructor()
    constructor(
        username: String?,
        e_mail: String?,
        phone_number: String?,
        info: ArrayList<PetDetails>,
        type: String?
    ) {
        this.username = username
        this.e_mail = e_mail
        this.phone_number = phone_number
        this.info = info
        this.type = type
    }
//    constructor(
//        username: String?,
//        e_mail: String?,
//        phone_number: String?,
//        ids: IDS?,
//        type: String?
//    ) {
//        this.username = username
//        this.e_mail = e_mail
//        this.phone_number = phone_number
//        this.ids = ids
//        this.type = type
//    }


//        username: String?,
//        name_of_animal: String?,
//        e_mail: String?,
//        phone_number: String?,
//        type_of_animal: String?,
//        breed: String?,
//        age_of_animal: String?,
//        sex_of_animal: String?,
//        userID: String?,
//        type: String?,
//        resource_catalog: String?,
//        deviceId: String?
//    ) {
//        this.username = username
//        this.name_of_animal = name_of_animal
//        this.e_mail = e_mail
//        this.phone_number = phone_number
//        this.type_of_animal = type_of_animal
//        this.breed = breed
//        this.age_of_animal = age_of_animal
//        this.sex_of_animal = sex_of_animal
//        this.userID = userID
//        this.type = type
//        this.resource_catalog = resource_catalog
//        this.deviceId = deviceId
//    }






}