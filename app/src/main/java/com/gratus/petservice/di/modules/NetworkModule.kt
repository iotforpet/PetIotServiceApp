package com.gratus.petservice.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gratus.petservice.service.*
import com.gratus.petservice.util.Interceptor.AppInterceptor
import com.gratus.petservice.util.constants.ServiceConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson {
        val builder =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun getRequestHeader(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(AppInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGetInfoService(retrofit: Retrofit): GetInfoService {
        return retrofit.create(GetInfoService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGetServiceInfoService(retrofit: Retrofit): GetServiceInfoService {
        return retrofit.create(GetServiceInfoService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideSignUpService(retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideResetPasswordService(retrofit: Retrofit): ResetPasswordService {
        return retrofit.create(ResetPasswordService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideDeviceService(retrofit: Retrofit): DeviceService {
        return retrofit.create(DeviceService::class.java)
    }


    @Provides
    @Singleton
    @JvmStatic
    fun provideTelService(retrofit: Retrofit): TelService {
        return retrofit.create(TelService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideAdminService(retrofit: Retrofit): AdminService {
        return retrofit.create(AdminService::class.java)
    }


    @Provides
    @Singleton
    @JvmStatic
    fun provideAppointService(retrofit: Retrofit): AppointService {
        return retrofit.create(AppointService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalysisService(retrofit: Retrofit): AnalysisService {
        return retrofit.create(AnalysisService::class.java)
    }
}