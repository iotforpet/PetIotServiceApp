package com.gratus.petservice.util.pref


interface AppPrefHelper {
    fun getUsername(): String?

    fun setUsername(username: String?)

    fun getPassword(): String?

    fun setPassword(password: String?)

    fun getResource(): String?

    fun setResource(resource: String?)

    fun getDevice(): String?

    fun setDevice(device: String?)

    fun getService(): String?

    fun setService(service: String?)


    fun getType(): String?

    fun setType(type: String?)

    fun getDeviceId(): String?

    fun setDeviceId(deviceId: String?)


    fun getUserId(): String?

    fun setUserId(userId: String?)

    fun getAnimalName(): String?

    fun setAnimalName(animalName: String?)

    fun getSensorList(): String?

    fun setSensorList(sensorList: String?)

}