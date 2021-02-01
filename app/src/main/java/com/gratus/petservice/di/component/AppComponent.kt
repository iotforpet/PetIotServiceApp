package com.gratus.petservice.di.component

import android.app.Application
import com.gratus.petservice.di.modules.*
import com.gratus.petservice.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, NetworkModule::class,
        InternetModule::class, AppPrefModule::class, ContextModule::class,
        ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

        fun internetModule(internetModule: InternetModule): Builder
    }
}