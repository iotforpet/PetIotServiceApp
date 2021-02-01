package com.gratus.petservice.viewModel.adapter

import com.gratus.petservice.view.interfaces.DeleteTelUserListerner
import javax.inject.Inject

class TelUserItemViewModel @Inject constructor(
    private var telUser: Int,
    private var mListener: DeleteTelUserListerner
)  {
    fun getTelUser(): Int? {
        return telUser
    }
    fun onItemClick() {
        mListener.onItemClick()
    }
}
