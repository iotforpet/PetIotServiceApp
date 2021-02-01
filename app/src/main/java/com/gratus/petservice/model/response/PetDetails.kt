package com.gratus.petservice.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PetDetails:Serializable {
    @SerializedName("type_of_animal")
    var type_of_animal: String? = null
    @SerializedName("breed")
    var breed: String? = null
    @SerializedName("age_of_animal")
    var age_of_animal: String? = null
    @SerializedName("sex_of_animal")
    var sex_of_animal: String? = null
    @SerializedName("userID")
    var userID: String? = null
    @SerializedName("resource_catalog")
    var resource_catalog: String? = null
    @SerializedName("deviceId")
    var deviceId: String? = null
    @SerializedName("name_of_animal")
    var name_of_animal: String? = null

    constructor(
        type_of_animal: String?,
        breed: String?,
        age_of_animal: String?,
        sex_of_animal: String?,
        userID: String?,
        resource_catalog: String?,
        deviceId: String?,
        name_of_animal: String?
    ) {
        this.type_of_animal = type_of_animal
        this.breed = breed
        this.age_of_animal = age_of_animal
        this.sex_of_animal = sex_of_animal
        this.userID = userID
        this.resource_catalog = resource_catalog
        this.deviceId = deviceId
        this.name_of_animal = name_of_animal
    }

    constructor()
    constructor(
        type_of_animal: String?,
        breed: String?,
        age_of_animal: String?,
        sex_of_animal: String?,
        name_of_animal: String?
    ) {
        this.type_of_animal = type_of_animal
        this.breed = breed
        this.age_of_animal = age_of_animal
        this.sex_of_animal = sex_of_animal
        this.name_of_animal = name_of_animal
    }
}