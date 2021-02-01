package com.gratus.petservice.util.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.gratus.petservice.util.constants.AppConstants.Companion.APP_PREF_NAME
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context) : AppPrefHelper {
    private var Username = "usernameKey"
    private var Password = "passwordKey"
    private var Resource = "resourceKey"
    private var Service = "serviceKey"
    private var Device = "deviceKey"
    private var DeviceID = "deviceIdkey"
    private var UserID = "userIdKey"
    private var Type = "typeKey"
    private var AnimalName = "AnimalNameKey"
    private var SensorList = "SensorListkey"

    private var gson = Gson()
    private var mPrefs: SharedPreferences
    init {
        mPrefs = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun getUsername(): String? {
        return mPrefs.getString(Username, "")
    }

    override fun setUsername(username: String?) {
        mPrefs.edit().putString(Username, username).apply()
    }

    override fun getPassword(): String? {
        return mPrefs.getString(Password, "")
    }

    override fun setPassword(password: String?) {
        mPrefs.edit().putString(Password, password).apply()
    }

    override fun getResource(): String? {
        return mPrefs.getString(Resource, null)
    }

    override fun setResource(resource: String?) {
        mPrefs.edit().putString(Resource, resource).apply()
    }

    override fun getDevice(): String? {
        return mPrefs.getString(Device, null)
    }

    override fun setDevice(device: String?) {
        mPrefs.edit().putString(Device, device).apply()
    }

    override fun getService(): String? {
        return mPrefs.getString(Service, null)
    }

    override fun setService(service: String?) {
        mPrefs.edit().putString(Service, service).apply()
    }

    override fun getType(): String? {
        return mPrefs.getString(Type, null)
    }

    override fun setType(type: String?) {
        mPrefs.edit().putString(Type, type).apply()
    }

    override fun getDeviceId(): String? {
        return mPrefs.getString(DeviceID, null)
    }

    override fun setDeviceId(deviceId: String?) {
        mPrefs.edit().putString(DeviceID, deviceId).apply()
    }

    override fun getUserId(): String? {
        return mPrefs.getString(UserID, null)
    }

    override fun setUserId(userId: String?) {
        mPrefs.edit().putString(UserID, userId).apply()
    }

    override fun getAnimalName(): String? {
        return mPrefs.getString(AnimalName, null)
    }

    override fun setAnimalName(animalName: String?) {
        mPrefs.edit().putString(AnimalName, animalName).apply()
    }

    override fun getSensorList(): String? {
        return mPrefs.getString(SensorList, null)
    }

    override fun setSensorList(sensorList: String?) {
        mPrefs.edit().putString(SensorList, sensorList).apply()
    }


    fun isClear(): Boolean {
        return false
    }

    fun setClear(clear: Boolean) {
        val editor = mPrefs.edit()
        editor.clear()
        editor.commit()
    }
}

