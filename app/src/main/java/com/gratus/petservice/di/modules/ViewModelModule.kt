package com.gratus.petservice.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gratus.petservice.di.factory.ViewModelFactory
import com.gratus.petservice.di.key.ViewModelKey
import com.gratus.petservice.service.AnalysisService
import com.gratus.petservice.viewModel.activity.*
import com.gratus.petservice.viewModel.fragment.AnalysisViewModel
import com.gratus.petservice.viewModel.fragment.AppointViewModel
import com.gratus.petservice.viewModel.fragment.HomeViewModel
import com.gratus.petservice.viewModel.fragment.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(welcomeViewModel: WelcomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun bindResetPasswordViewModel(resetPasswordViewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindEditProfileViewModel(editProfileViewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SensorActiveDeactiveViewModel::class)
    abstract fun bindSensorActiveDeactiveViewModel(sensorActiveDeactiveViewModel: SensorActiveDeactiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TelUsersViewModel::class)
    abstract fun bindTelUsersViewModel(telUsersViewModel: TelUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SensorFrequencyViewModel::class)
    abstract fun bindSensorFrequencyViewModel(sensorFrequencyViewModel: SensorFrequencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUsersViewModel(usersViewModel: UsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangeUsersViewModel::class)
    abstract fun bindChangeUsersViewModel(changeUsersViewModel: ChangeUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(AppointViewModel::class)
    abstract fun bindAppointViewModel(appointViewModel: AppointViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel?): ViewModel?


    @Binds
    @IntoMap
    @ViewModelKey(AnalysisViewModel::class)
    abstract fun bindAnalysisViewModel(analysisViewModel: AnalysisViewModel?): ViewModel?
}