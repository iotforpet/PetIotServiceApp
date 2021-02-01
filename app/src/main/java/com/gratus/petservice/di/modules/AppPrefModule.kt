package com.gratus.petservice.di.modules

import com.gratus.petservice.util.pref.AppPrefHelper
import com.gratus.petservice.util.pref.AppPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppPrefModule {
    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): AppPrefHelper {
        return appPreferencesHelper
    }
}