package com.gratus.petservice.viewModel.activity


import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.common.UserAdapterModel
import com.gratus.petservice.model.request.TelUserRequest
import com.gratus.petservice.model.request.UserRequest
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.model.response.UserResponse
import com.gratus.petservice.repository.*
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.viewModel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersViewModel @Inject constructor(
        private var userRepo: UserRepo,
) : BaseViewModel() {
    var profileResponse: MutableLiveData<ProfileResponse> =
            MutableLiveData<ProfileResponse>()
    var userResponse: MutableLiveData<UserResponse> =
        MutableLiveData<UserResponse>()
    var userRequest: UserRequest = UserRequest()
    var userAdapterModels:ArrayList<UserAdapterModel> = ArrayList()
    fun hitUser() {
        userRequest.type ="user"
        if (isNetworkConnected()) {
                compositeDisposable.add(
                    userRepo.getUsersResponse(userRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<UserResponse?>() {
                                    override fun onSuccess(telUserResponses: UserResponse) {
                                        userResponse.value = telUserResponses
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                userResponse.value =
                                                    UserResponse(
                                                                jObjError.getInt("status"),
                                                                false
                                                        )
                                            } catch (ex: JSONException) {
                                                ex.printStackTrace()
                                            } catch (ex: IOException) {
                                                ex.printStackTrace()
                                            }
                                        }
                                    }
                                })
                )
        } else {
            userResponse.value =
                UserResponse(
                            AppConstants.NETWORK_CODE_EXP,
                            false
                    )
        }
    }

    fun getAdaptermodel(value: UserResponse) {
        userAdapterModels = ArrayList()
        for (prop in value.output) {
            for (prop1 in prop.ids!!) {
                if (prop1.resource_catalog != null) {
                    var userAdapterModel: UserAdapterModel =
                        UserAdapterModel(prop.e_mail, prop1.userID,prop.name)
                    userAdapterModels.add(userAdapterModel)
                }
            }
        }
    }

    fun hitDelUser(position: String?, eMail: String?) {
        userRequest.resource_catalog = null
        userRequest.userID = position
        userRequest.e_mail=eMail
        userRequest.deviceId = null
        if (isNetworkConnected()) {
            compositeDisposable.add(
                userRepo.getDeleteResponse(userRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                        override fun onSuccess(profileResponses: ProfileResponse) {
                            profileResponse.value = profileResponses
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    profileResponse.value =
                                        ProfileResponse(
                                            jObjError.getInt("status"),
                                            false
                                        )
                                } catch (ex: JSONException) {
                                    ex.printStackTrace()
                                } catch (ex: IOException) {
                                    ex.printStackTrace()
                                }
                            }
                        }
                    })
            )
        } else {
            profileResponse.value =
                ProfileResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }

    fun hitAddUser(position: String,resource: String,deviceId: String,email: String) {
        userRequest.resource_catalog = resource
        userRequest.userID = position
        userRequest.e_mail = email
        userRequest.deviceId = deviceId
        if (isNetworkConnected()) {
            compositeDisposable.add(
                userRepo.getAddResponse(userRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                        override fun onSuccess(profileResponses: ProfileResponse) {
                            profileResponse.value = profileResponses
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val response = e.response()
                                try {
                                    val jObjError =
                                        JSONObject(response!!.errorBody()!!.string())
                                    println(jObjError)
                                    profileResponse.value =
                                        ProfileResponse(
                                            jObjError.getInt("status"),
                                            false
                                        )
                                } catch (ex: JSONException) {
                                    ex.printStackTrace()
                                } catch (ex: IOException) {
                                    ex.printStackTrace()
                                }
                            }
                        }
                    })
            )
        } else {
            profileResponse.value =
                ProfileResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }


}
