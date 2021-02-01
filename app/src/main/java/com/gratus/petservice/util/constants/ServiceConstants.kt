package com.gratus.petservice.util.constants

interface ServiceConstants {
    companion object {
        const val BASE_URL = "http://192.168.1.142:9293/"
        const val WELCOME_URL = "petMobile/login"
        const val GET_INFO = "petMobile/getinfo"
        const val RESET_PASSWORD__URL = "petMobile/resetPassword"
        const val SIGNUP__URL = "petMobile/register"
        const val EDIT_PROFILE__URL = "petMobile/editProfile";
        const val PROFILE__URL = "petMobile/profile";
        const val DEVICE_STATUS__URL = "petMobile/getDeviceWebStatus";
        const val UPDATE_DEVICE_STATUS__URL = "petMobile/updateWebStatus";
        const val TELUSERS__URL = "petMobile/gettelegram";
        const val ADDTELUSERS__URL = "petMobile/addtelegram";
        const val DELETETELUSERS__URL = "petMobile/deletetelegram";
        const val GET_SERVICE_INFO = "petMobile/getServiceInfo"
        const val UPDATE_SENSOR_FREQ__URL = "petMobile/updateServiceLimt";
        const val GET_USER = "petMobile/getUsers";
        const val REMOVE_USER = "petMobile/removeuser";
        const val ADD_USER = "petMobile/adduser";
        const val APPOINT__URL = "petMobile/petAppoint";
        const val HOME__URL = "petMobile/getHome";
        const val ANALYSIS_TODAY_URL = "petMobile/getToday";
        const val ANALYSIS_WEEK_URL = "petMobile/getWeek";
    }
}