package com.gratus.petservice

import android.content.Context
import android.net.ConnectivityManager
import com.gratus.petservice.di.component.AppComponent
import com.gratus.petservice.di.component.DaggerAppComponent

import com.gratus.petservice.di.modules.InternetModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    private lateinit var appComponent: AppComponent
    private lateinit var connectivityManager: ConnectivityManager

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        appComponent = DaggerAppComponent.builder().application(this)
            .internetModule(InternetModule(connectivityManager))
            .build()
        appComponent.inject(this)
        return appComponent
    }
}