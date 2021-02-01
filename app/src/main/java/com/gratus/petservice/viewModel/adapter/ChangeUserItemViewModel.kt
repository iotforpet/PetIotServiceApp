package com.gratus.petservice.viewModel.adapter

import com.gratus.petservice.model.common.UserAdapterModel
import com.gratus.petservice.model.response.Users
import com.gratus.petservice.view.interfaces.DeleteUserListerner
import javax.inject.Inject

class ChangeUserItemViewModel @Inject constructor(
    private var user: UserAdapterModel,
    private var mListener: DeleteUserListerner
)  {
    fun getUser(): UserAdapterModel? {
        return user
    }
    fun onItemClick() {
        mListener.onItemClick()
    }
}
