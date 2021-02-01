package com.gratus.petservice.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityWebadaBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.SensorActiveDeactiveViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class SensorActivityDeactiveActivity:BaseActivity() {
    lateinit var activityWebadaBinding: ActivityWebadaBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var sensorActiveDeactiveViewModel: SensorActiveDeactiveViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWebadaBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_webada
        )
        sensorActiveDeactiveViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SensorActiveDeactiveViewModel::class.java)
        activityWebadaBinding.sensorActiveDeactiveViewModel = sensorActiveDeactiveViewModel
        activityWebadaBinding.setLifecycleOwner(this)
        sensorActiveDeactiveViewModel.hitDeviceStatus()
        sensorActiveDeactiveViewModel.deviceStatusResponseMutableLiveData.observe(this, Observer {
            if (it.success) {
                if(it.deviceStatus?.water==1){
                    activityWebadaBinding.waterS.isChecked = true
                }
                if(it.deviceStatus?.food==1){
                    activityWebadaBinding.petFoodS.isChecked = true
                }
                if(it.deviceStatus?.pet==1){
                    activityWebadaBinding.petWeightS.isChecked = true
                }
                if(it.deviceStatus?.temp==1){
                    activityWebadaBinding.tempS.isChecked = true
                }
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activityWebadaBinding.parent)
                } else {
                    DynamicToast.makeError(
                            this@SensorActivityDeactiveActivity,
                            "Device not Found"
                    ).show()
                }
            }
        })
        activityWebadaBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
        activityWebadaBinding.petWeightS.setOnClickListener { v ->
            sensorActiveDeactiveViewModel.hitUpdateDeviceStatus("W_2",
                "W-Sensors",activityWebadaBinding.petWeightS.isChecked) }
        activityWebadaBinding.petFoodS.setOnClickListener { v ->
            sensorActiveDeactiveViewModel.hitUpdateDeviceStatus("W_1",
                "W-Sensors",activityWebadaBinding.petFoodS.isChecked) }
        activityWebadaBinding.tempS.setOnClickListener { v ->
            sensorActiveDeactiveViewModel.hitUpdateDeviceStatus("temp_1",
                "T-Sensors",activityWebadaBinding.tempS.isChecked) }
        activityWebadaBinding.waterS.setOnClickListener { v ->
            sensorActiveDeactiveViewModel.hitUpdateDeviceStatus("WL_1",
                "WL-Sensors",activityWebadaBinding.waterS.isChecked) }
        sensorActiveDeactiveViewModel.profileResponseMutableLiveData.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                    this@SensorActivityDeactiveActivity,
                    it.message
                ).show()
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activityWebadaBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@SensorActivityDeactiveActivity,
                        "Device not Found"
                    ).show()
                }
            }
        })
    }


    fun onNetworkConnectionChanged(isConnected: Boolean) {
            showSnack(isConnected, activityWebadaBinding.parent)
        setIntial(false)
    }


    override fun onBackPressed() {
        finish()
    }
}