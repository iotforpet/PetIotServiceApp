package com.gratus.petservice.view.interfaces

import com.gratus.petservice.model.common.UserAdapterModel

interface DeleteUserListernerAdapter {
    fun onItemClick(position:UserAdapterModel)
}