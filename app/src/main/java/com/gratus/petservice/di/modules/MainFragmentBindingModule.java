package com.gratus.petservice.di.modules;


import com.gratus.petservice.view.fragment.AnalysisFragment;
import com.gratus.petservice.view.fragment.AppointFragment;
import com.gratus.petservice.view.fragment.HomeFragment;
import com.gratus.petservice.view.fragment.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract SettingsFragment provideSettingsFragment();

    @ContributesAndroidInjector
    abstract AppointFragment provideAppointFragment();

    @ContributesAndroidInjector
    abstract AnalysisFragment provideAnalysisFragment();

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();
}