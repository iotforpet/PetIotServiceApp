package com.gratus.petservice.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivitySensorTfreqBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.NETWORK_CODE_EXP
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.viewModel.activity.SensorFrequencyViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject


class SensorFrequencyActivity:BaseActivity() {
    lateinit var activitysSensorTfreqBinding: ActivitySensorTfreqBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var sensorFrequencyViewModel: SensorFrequencyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitysSensorTfreqBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_sensor_tfreq
        )
        sensorFrequencyViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SensorFrequencyViewModel::class.java)
        activitysSensorTfreqBinding.sensorFrequencyViewModel = sensorFrequencyViewModel
        activitysSensorTfreqBinding.setLifecycleOwner(this)
        sensorFrequencyViewModel.hitSensorStatus()
        activitysSensorTfreqBinding.tempP.minValue=5
        activitysSensorTfreqBinding.tempP.maxValue=60
        activitysSensorTfreqBinding.waterP.minValue=5
        activitysSensorTfreqBinding.waterP.maxValue=60
        activitysSensorTfreqBinding.petWeightP.minValue=5
        activitysSensorTfreqBinding.petWeightP.maxValue=60

        sensorFrequencyViewModel.getServiceInfoResponseMutableLiveData.observe(this, Observer {
            if (it.success) {
                activitysSensorTfreqBinding.tempP.value = it.getServiceInfo?.T_Sensors!!
                activitysSensorTfreqBinding.waterP.value = it.getServiceInfo?.WL_Sensors!!
                activitysSensorTfreqBinding.petWeightP.value = it.getServiceInfo?.W_Sensors!!
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activitysSensorTfreqBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@SensorFrequencyActivity,
                        "Device not Found"
                    ).show()
                }
            }
        })
        activitysSensorTfreqBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
        activitysSensorTfreqBinding.updateImg.setOnClickListener{v-> updateSensor()}
//        activitysSensorTfreqBinding.tempP.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
//            picker.value = if (newVal < oldVal) oldVal - 5 else oldVal + 5
//        })
//        activitysSensorTfreqBinding.waterP.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
//            picker.value = if (newVal < oldVal) oldVal - 5 else oldVal + 5
//        })
//        activitysSensorTfreqBinding.petWeightP.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
//            picker.value = if (newVal < oldVal) oldVal - 5 else oldVal + 5
//        })

        sensorFrequencyViewModel.profileResponseMutableLiveData.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                    this@SensorFrequencyActivity,
                    it.message
                ).show()
            } else {
                if (it.status === NETWORK_CODE_EXP) {
                    showSnack(true, activitysSensorTfreqBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@SensorFrequencyActivity,
                        "Device not Found"
                    ).show()
                }
            }
        })
    }

    private fun updateSensor() {
        var sensor: ArrayList<String> = ArrayList()
        sensor.add("T-Sensors")
        sensor.add("W-Sensors")
        sensor.add("WL-Sensors")
        var freq: ArrayList<Int> = ArrayList()
        freq.add(activitysSensorTfreqBinding.tempP.value)
        freq.add(activitysSensorTfreqBinding.petWeightP.value)
        freq.add(activitysSensorTfreqBinding.waterP.value)
        sensorFrequencyViewModel.hitUpdateSensorStatus(sensor,freq)
    }


    fun onNetworkConnectionChanged(isConnected: Boolean) {
            showSnack(isConnected, activitysSensorTfreqBinding.parent)
        setIntial(false)
    }


    override fun onBackPressed() {
        finish()
    }
}