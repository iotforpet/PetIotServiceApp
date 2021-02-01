package com.gratus.petservice.di.modules

import androidx.recyclerview.widget.LinearLayoutManager
import com.gratus.petservice.view.activity.TelUsersActivity
import com.gratus.petservice.view.activity.UsersActivity
import com.gratus.petservice.view.adapter.ChangeUserListAdapter
import com.gratus.petservice.view.adapter.TelListAdapter
import com.gratus.petservice.view.adapter.UserListAdapter
import dagger.Module
import dagger.Provides
@Module
class AdapterBindingModule {

    @Provides
    fun provideTelListAdapter(): TelListAdapter {
        return TelListAdapter(ArrayList())
    }

    @Provides
    fun provideUserListAdapter(): UserListAdapter {
        return UserListAdapter(ArrayList())
    }

    @Provides
    fun provideChangeUserListAdapter(): ChangeUserListAdapter {
        return ChangeUserListAdapter(ArrayList())
    }

    @Provides
    fun provideLinearLayoutManager(telUsersActivity: TelUsersActivity): LinearLayoutManager {
        return LinearLayoutManager(telUsersActivity)
    }
}