package com.gratus.petservice.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityMainBinding
import com.gratus.petservice.util.constants.AppConstants.Companion.FRAGMENT_HOME
import com.gratus.petservice.util.constants.AppConstants.Companion.FRAGMENT_OTHER
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.view.fragment.*
import com.gratus.petservice.viewModel.activity.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //        ((BaseApplication) getApplicationContext()).getAppComponent()
//                .inject(BaseApplication)
//                .inject(this);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        activityMainBinding.mainViewModel=mainViewModel
        activityMainBinding.setLifecycleOwner(this)
        viewFragment(HomeFragment(), FRAGMENT_HOME)
        setupNavMenu()
    }

    open fun setupNavMenu() {
        activityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> {
                    viewFragment(HomeFragment(), FRAGMENT_HOME)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.analysis -> {
                    viewFragment(AnalysisFragment(), FRAGMENT_OTHER)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.appoint -> {
                    viewFragment(AppointFragment(), FRAGMENT_OTHER)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    viewFragment(SettingsFragment(), FRAGMENT_OTHER)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected, activityMainBinding.parent)
        setIntial(false)
    }

    open fun viewFragment(fragment: Fragment, name: String) {
        val fragmentTransaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        /*if(name.equals( FRAGMENT_OTHER) ) {
            fragmentTransaction.sta
            fragmentTransaction.addToBackStack(name);
        }*/fragmentTransaction.commit()
    }
}