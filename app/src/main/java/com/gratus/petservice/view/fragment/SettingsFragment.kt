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
import com.gratus.petservice.databinding.FragmentSettingsBinding
import com.gratus.petservice.view.activity.*
import com.gratus.petservice.view.base.BaseFragment
import com.gratus.petservice.viewModel.fragment.SettingsViewModel
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    lateinit var fragmentSettingsBinding: FragmentSettingsBinding
    lateinit var mRootView: View

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var settingsViewModel: SettingsViewModel

    fun SettingsFragment() {}
    companion object {

        fun newInstance(): SettingsFragment {
            var fragment = SettingsFragment()
            val args = Bundle()
            fragment.arguments=args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSettingsBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_settings, container, false)
        mRootView = fragmentSettingsBinding.getRoot()
        fragmentSettingsBinding.settingsViewModel=settingsViewModel
        fragmentSettingsBinding.lifecycleOwner=this
        fragmentSettingsBinding.name.setOnClickListener { v -> goProfile() }
        fragmentSettingsBinding.password.setOnClickListener { v -> goRestPasswrord() }
        fragmentSettingsBinding.device.setOnClickListener { v -> goDeviceChange() }
        fragmentSettingsBinding.telegram.setOnClickListener { v -> goTelegram() }
        fragmentSettingsBinding.sensorFreq.setOnClickListener { v -> goFreq() }
        fragmentSettingsBinding.sensorStatus.setOnClickListener { v -> goStatus() }
        fragmentSettingsBinding.logout.setOnClickListener { v -> logout() }
        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)
    }

    fun goProfile() {
        val intent = Intent(getActivity(), ProfileActivity::class.java)
        startActivity(intent)
    }

    fun goRestPasswrord() {
        val intent = Intent(getActivity(), ResetPasswordActivity::class.java)
        startActivity(intent)
    }

    fun goDeviceChange() {
        val intent = Intent(getActivity(), ChangeUsersActivity::class.java)
        startActivity(intent)
    }


    fun goTelegram() {
        val intent = Intent(getActivity(), TelUsersActivity::class.java)
        startActivity(intent)
    }

    fun goFreq() {
        val intent = Intent(getActivity(), SensorFrequencyActivity::class.java)
        startActivity(intent)
    }

    fun goStatus() {
        val intent = Intent(getActivity(), SensorActivityDeactiveActivity::class.java)
        startActivity(intent)
    }

    fun logout() {
        getPrefs()!!.setClear(true)
        getActivity()!!.finish()
        System.exit(0)
    }
}