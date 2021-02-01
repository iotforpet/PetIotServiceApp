package com.gratus.petservice.viewModel.activity


import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.common.UserDetails
import com.gratus.petservice.model.request.ProfileRequest
import com.gratus.petservice.model.request.RegistrationRequest
import com.gratus.petservice.model.response.PetDetails
import com.gratus.petservice.model.response.ProfileResponse
import com.gratus.petservice.repository.ProfileRepo
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

class ProfileViewModel @Inject constructor(
        private var profileRepo: ProfileRepo,
) : BaseViewModel() {
    var profileResponseMutableLiveData: MutableLiveData<ProfileResponse> =
        MutableLiveData<ProfileResponse>()
    var profileRequest: ProfileRequest = ProfileRequest()
    var petDetails:PetDetails  = PetDetails()
    fun hitProfile() {
        if (isNetworkConnected()) {
            profileRequest.username=(getPrefs()!!.getUsername())
            profileRequest.userID=(getPrefs()!!.getUserId())
                compositeDisposable.add(
                        profileRepo.getProfileResponse(profileRequest)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver<ProfileResponse?>() {
                                    override fun onSuccess(profileResponse: ProfileResponse) {
//                                        getProfile(profileResponseMutableLiveData.value!!.userDetails)
                                        profileResponseMutableLiveData.value = profileResponse
                                    }

                                    override fun onError(e: Throwable) {
                                        if (e is HttpException) {
                                            val response = e.response()
                                            try {
                                                val jObjError =
                                                        JSONObject(response!!.errorBody()!!.string())
                                                println(jObjError)
                                                profileResponseMutableLiveData.value =
                                                        ProfileResponse(
                                                                jObjError.getInt("status"),
                                                                false, jObjError.getString("message")
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
            profileResponseMutableLiveData.value =
                ProfileResponse(
                        AppConstants.NETWORK_CODE_EXP,
                        false
                )
        }
    }

//    fun getProfile(userDetails: UserDetails?) {
//        for (key in userDetails?.info!!.keys) {
//            if(key.equals(getPrefs()?.getUserId())) {
//              petDetails  = PetDetails()
//                petDetails = PetDetails(
//                    userDetails?.info?.get(key)?.type_of_animal.toString(),
//                    userDetails?.info?.get(key)?.breed.toString(),
//                    userDetails?.info?.get(key)?.age_of_animal.toString(),
//                    userDetails?.info?.get(key)?.sex_of_animal.toString(),
//                    userDetails?.info?.get(key)?.name_of_animal.toString(),
//                )
//            }
//        }
//    }
}
