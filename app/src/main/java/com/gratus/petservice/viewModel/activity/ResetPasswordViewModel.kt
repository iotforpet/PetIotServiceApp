package com.gratus.petservice.viewModel.activity


import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.gratus.petservice.model.request.ResetPasswordRequest
import com.gratus.petservice.model.response.LoginResponse
import com.gratus.petservice.repository.ResetPasswordRepo
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

class ResetPasswordViewModel @Inject constructor(
    private var resetPasswordRepo: ResetPasswordRepo,
) : BaseViewModel() {
    var loginResponses: MutableLiveData<LoginResponse> =
        MutableLiveData<LoginResponse>()
    var resetPasswordRequest: ResetPasswordRequest = ResetPasswordRequest()

    fun hitReset() {
        if (isNetworkConnected()) {
            if (resetPasswordRequest.isEmailValid() && resetPasswordRequest.isPasswordLengthGreaterThan7()
                && resetPasswordRequest.isConfirmPasswordLengthGreaterThan7()
            ) {
                resetPasswordRequest.getButtonVisibility(false)
                getDelay(true)
                compositeDisposable.add(
                    resetPasswordRepo.getLoginResponse(resetPasswordRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<LoginResponse?>() {
                            override fun onSuccess(loginResponse: LoginResponse) {
                                loginResponses.value = loginResponse
                            }

                            override fun onError(e: Throwable) {
                                if (e is HttpException) {
                                    val response = e.response()
                                    try {
                                        val jObjError =
                                            JSONObject(response!!.errorBody()!!.string())
                                        println(jObjError)
                                        loginResponses.value =
                                            LoginResponse(
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
            }
        } else {
            loginResponses.value =
                LoginResponse(
                    AppConstants.NETWORK_CODE_EXP,
                    false
                )
        }
    }

    fun getDelay(b: Boolean) {
        val handler = Handler()
        handler.postDelayed({ resetPasswordRequest.getProgressVisibility(b) }, 100)
    }
}
