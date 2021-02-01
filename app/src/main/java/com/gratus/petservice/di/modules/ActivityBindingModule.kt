package com.gratus.petservice.di.modules

import com.gratus.petservice.view.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindWelcomeActivity(): WelcomeActivity
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity
    @ContributesAndroidInjector
    abstract fun bindSignUpActivity(): SignUpActivity
    @ContributesAndroidInjector
    abstract fun bindResetPasswordActivity(): ResetPasswordActivity
    @ContributesAndroidInjector
    abstract fun bindProfileActivity(): ProfileActivity
    @ContributesAndroidInjector
    abstract fun bindEditProfileActivity(): EditProfileActivity
    @ContributesAndroidInjector
    abstract fun bindSensorActivityDeactiveActivity(): SensorActivityDeactiveActivity
    @ContributesAndroidInjector(modules = [AdapterBindingModule::class])
    abstract fun bindTelUsersActivity(): TelUsersActivity
    @ContributesAndroidInjector
    abstract fun bindSensorFrequencyActivity(): SensorFrequencyActivity
    @ContributesAndroidInjector(modules = [AdapterBindingModule::class])
    abstract fun bindUsersActivity(): UsersActivity
    @ContributesAndroidInjector(modules = [AdapterBindingModule::class])
    abstract fun bindChangeUsersActivity(): ChangeUsersActivity
    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}
