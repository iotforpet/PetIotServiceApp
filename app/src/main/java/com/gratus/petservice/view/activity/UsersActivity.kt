package com.gratus.petservice.view.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gratus.petservice.R
import com.gratus.petservice.databinding.ActivityUserAddDeleteBinding
import com.gratus.petservice.model.common.*
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.adapter.TelListAdapter
import com.gratus.petservice.view.adapter.UserListAdapter
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.view.interfaces.DeleteTelUserListernerAdapter
import com.gratus.petservice.view.interfaces.DeleteUserListernerAdapter
import com.gratus.petservice.viewModel.activity.TelUsersViewModel
import com.gratus.petservice.viewModel.activity.UsersViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import java.util.*
import javax.inject.Inject


class UsersActivity : BaseActivity(), DeleteUserListernerAdapter {
    lateinit var activityUserAddDeleteBinding: ActivityUserAddDeleteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var userListAdapter: UserListAdapter

    var mLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    lateinit var usersViewModel: UsersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserAddDeleteBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_user_add_delete
        )
        usersViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel::class.java)
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
                        this@UsersActivity,
                        "Users not found"
                    ).show()
                }
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityUserAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Users not found"
                    ).show()
                }
            }
        })
        usersViewModel.profileResponse.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                    this@UsersActivity,
                    it.message
                ).show()
                usersViewModel.hitUser()
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityUserAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Users not found"
                    ).show()
                }
            }
        })
        activityUserAddDeleteBinding.addImg.setOnClickListener{ v->addTelegramUser()}
        activityUserAddDeleteBinding.logout.setOnClickListener { v -> logout() }
    }

    private fun addTelegramUser() {
        val layoutInflaterAndroid = LayoutInflater.from(this)
        val mView: View = layoutInflaterAndroid.inflate(R.layout.add_user_dialog, null)
        val alertDialogBuilderUserInput: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilderUserInput.setView(mView)
        val categoryEditText1 = mView.findViewById(R.id.userInputDialog1) as EditText
        val categoryEditText2 = mView.findViewById(R.id.userInputDialog2) as EditText
        val categoryEditText3 = mView.findViewById(R.id.userInputDialog3) as EditText
        val categoryEditText4 = mView.findViewById(R.id.userInputDialog4) as EditText
        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton("Activate", DialogInterface.OnClickListener { dialogBox, id ->
                if(categoryEditText1.text.length<3) {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Enter valid user Id"
                    ).show()
                }
                else if(categoryEditText2.text.length<10) {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Enter valid resource catalog"
                    ).show()
                }
                else if(categoryEditText3.text.length<1) {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Enter valid device Id"
                    ).show()
                }
                else if(categoryEditText4.text.length<1) {
                    DynamicToast.makeError(
                        this@UsersActivity,
                        "Enter valid email address"
                    ).show()
                }
                else{
                    usersViewModel.hitAddUser(categoryEditText1.text.toString(),categoryEditText2.text.toString(),categoryEditText3.text.toString(),categoryEditText4.text.toString())
                }
            })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialogBox, id -> dialogBox.cancel() })

        val alertDialogAndroid: AlertDialog = alertDialogBuilderUserInput.create()
        alertDialogAndroid.show()
    }

    private fun updateReport() {
        userListAdapter.addItems(usersViewModel.userAdapterModels)
    }

    private fun setTelegramUp() {
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activityUserAddDeleteBinding.recView.layoutManager = mLayoutManager
        activityUserAddDeleteBinding.recView.itemAnimator = DefaultItemAnimator()
        activityUserAddDeleteBinding.recView.adapter = userListAdapter
        userListAdapter.setmListener(this)
    }

    fun logout() {
        getPrefs()!!.setClear(true)
        this.finish()
        System.exit(0)
    }

    override fun onItemClick(position: UserAdapterModel) {
        usersViewModel.hitDelUser(position.userID,position.e_mail)
    }
}