package com.gratus.petservice.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityChangeUserBinding
import com.gratus.petservice.model.common.*
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.adapter.ChangeUserListAdapter
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.view.interfaces.DeleteUserListernerAdapter
import com.gratus.petservice.viewModel.activity.ChangeUsersViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import java.util.*
import javax.inject.Inject


class ChangeUsersActivity : BaseActivity(), DeleteUserListernerAdapter {
    lateinit var activityUserAddDeleteBinding: ActivityChangeUserBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userListAdapter: ChangeUserListAdapter

    var mLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    lateinit var usersViewModel: ChangeUsersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserAddDeleteBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_change_user
        )
        usersViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ChangeUsersViewModel::class.java)
        activityUserAddDeleteBinding.usersViewModel = usersViewModel
        activityUserAddDeleteBinding.lifecycleOwner = this
        setTelegramUp()
        usersViewModel.hitUser()
        usersViewModel.userResponse.observe(this, Observer {
            if (it.success) {
                if (it.output?.size!! > 0) {
                    usersViewModel.getAdaptermodel(it)
                    updateReport()
                    userListAdapter.notifyDataSetChanged()
                } else {
                    DynamicToast.makeError(
                        this@ChangeUsersActivity,
                        "Users not found"
                    ).show()
                }
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityUserAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@ChangeUsersActivity,
                        "Users not found"
                    ).show()
                }
            }
        })
        usersViewModel.profileResponse.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                    this@ChangeUsersActivity,
                    it.message
                ).show()
                usersViewModel.hitUser()
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityUserAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@ChangeUsersActivity,
                        "Users not found"
                    ).show()
                }
            }
        })
        activityUserAddDeleteBinding.backArrowImg.setOnClickListener{ v->onBackPressed()}
    }


    private fun updateReport() {
        userListAdapter.addItems(usersViewModel.userAdapterModels)
    }

    private fun setTelegramUp() {
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activityUserAddDeleteBinding.recView.layoutManager = mLayoutManager
        activityUserAddDeleteBinding.recView.itemAnimator = DefaultItemAnimator()
        activityUserAddDeleteBinding.recView.adapter = userListAdapter
        userListAdapter.setmListener(this,getPrefs())
    }

    fun logout() {
        getPrefs()!!.setClear(true)
        this.finish()
        System.exit(0)
    }

    override fun onItemClick(position: UserAdapterModel) {
        getPrefs()?.setUserId(position.userID)
        userListAdapter.notifyDataSetChanged()
    }
}