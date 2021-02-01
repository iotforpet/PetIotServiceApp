package com.gratus.petservice.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.FragmentHomeBinding
import com.gratus.petservice.databinding.FragmentSettingsBinding
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.activity.ProfileActivity
import com.gratus.petservice.view.activity.ResetPasswordActivity
import com.gratus.petservice.view.base.BaseFragment
import com.gratus.petservice.viewModel.fragment.HomeViewModel
import com.gratus.petservice.viewModel.fragment.SettingsViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var mRootView: View

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel

    fun HomeFragment() {}
    companion object {

        fun newInstance(): HomeFragment {
            var fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments=args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_home, container, false)
        mRootView = fragmentHomeBinding.getRoot()
        fragmentHomeBinding.homeViewModel=homeViewModel
        fragmentHomeBinding.lifecycleOwner=this
        homeViewModel.hitHome()
        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.homeRespoonse.observe(activity!!) { homeRespoonse ->
            if (homeRespoonse.success) {
                fragmentHomeBinding.nameTv.setText("Welcome !")
                fragmentHomeBinding.serviceTv.setText(homeRespoonse.appoint?.get(0)?.typeOfService)
                fragmentHomeBinding.serviceTv1.setText(homeRespoonse.appoint?.get(1)?.typeOfService)
                fragmentHomeBinding.da.setText(homeRespoonse.appoint?.get(0)?.timeStamp)
                fragmentHomeBinding.da1.setText(homeRespoonse.appoint?.get(1)?.timeStamp)
                if(homeRespoonse.sensorStatus?.output.temp_1==1){
                    fragmentHomeBinding.tempwork.setText("Sensor\nWorking")
                }
                else{
                    fragmentHomeBinding.tempwork.setText("Sensor \nRepair")
                }
                if(homeRespoonse.sensorStatus?.output.w_1==1){
                    fragmentHomeBinding.pwwork.setText("Sensor \nWorking")
                }
                else{
                    fragmentHomeBinding.pwwork.setText("Sensor \nRepair")
                }
                if(homeRespoonse.sensorStatus?.output.w_2==1){
                    fragmentHomeBinding.pwlwork.setText("Sensor \nWorking")
                }
                else{
                    fragmentHomeBinding.pwlwork.setText("Sensor \nRepair")
                }
                if(homeRespoonse.sensorStatus?.output.wl_1==1){
                    fragmentHomeBinding.wlwork.setText("Sensor \nWorking")
                }
                else{
                    fragmentHomeBinding.wlwork.setText("Sensor \nRepair")
                }

                if(homeRespoonse.userSensorStatus?.output.temp_1==1){
                    fragmentHomeBinding.tempstat.setText("Sensor Active")
                }
                else{
                    fragmentHomeBinding.tempstat.setText("Sensor Deactive")
                }
                if(homeRespoonse.userSensorStatus?.output.w_1==1){
                    fragmentHomeBinding.pwstat.setText("Sensor Active")
                }
                else{
                    fragmentHomeBinding.pwstat.setText("Sensor Deactive")
                }
                if(homeRespoonse.userSensorStatus?.output.w_2==1){
                    fragmentHomeBinding.pwlstat.setText("Sensor Active")
                }
                else{
                    fragmentHomeBinding.pwlstat.setText("Sensor Deactive")
                }
                if(homeRespoonse.userSensorStatus?.output.wl_1==1){
                    fragmentHomeBinding.wlstat.setText("Sensor Active")
                }
                else{
                    fragmentHomeBinding.wlstat.setText("Sensor Deactive")
                }
                for (i in 0..homeRespoonse.current?.output.size-1) {
                    if (homeRespoonse.current?.output.get(i).sensorID == "temp_1") {
                        fragmentHomeBinding.temp.setText(homeRespoonse.current?.output.get(i).sensorData.toString()+" C")
                        fragmentHomeBinding.tempstatus.setText(homeRespoonse.current?.output.get(i).status.toString())
                        fragmentHomeBinding.tempdt.setText(homeRespoonse.current?.output.get(i).date.toString())
                    }
                    if (homeRespoonse.current?.output.get(i).sensorID == "WL_1") {
                        fragmentHomeBinding.wl.setText(homeRespoonse.current?.output.get(i).sensorData.toString()+" cm")
                        fragmentHomeBinding.wlstatus.setText(homeRespoonse.current?.output.get(i).status.toString())
                        fragmentHomeBinding.wldt.setText(homeRespoonse.current?.output.get(i).date.toString())
                    }
                    if (homeRespoonse.current?.output.get(i).sensorID == "W_1") {
                        fragmentHomeBinding.pwl.setText(homeRespoonse.current?.output.get(i).sensorData.toString()+" gram")
                        fragmentHomeBinding.pwlstatus.setText(homeRespoonse.current?.output.get(i).status.toString()+" per day")
                        fragmentHomeBinding.pwldt.setText(homeRespoonse.current?.output.get(i).date.toString())
                    }
                    if (homeRespoonse.current?.output.get(i).sensorID == "W_2") {
                        fragmentHomeBinding.pw.setText(homeRespoonse.current?.output.get(i).sensorData.toString()+" Kg")
                        fragmentHomeBinding.pwstatus.setText(homeRespoonse.current?.output.get(i).status.toString())
                        fragmentHomeBinding.pwdt.setText(homeRespoonse.current?.output.get(i).date.toString())
                    }
                }

            } else {
                if (homeRespoonse.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, fragmentHomeBinding.parent)
                } else {
                    DynamicToast.makeError(activity!!, "No data found")
                            .show()
                }
            }
        }
    }
}