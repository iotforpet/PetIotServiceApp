package com.gratus.petservice.view.activity

import android.app.AlertDialog
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
import com.gratus.petservice.databinding.ActivityTelAddDeleteBinding
import com.gratus.petservice.model.common.*
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.adapter.TelListAdapter
import com.gratus.petservice.view.base.BaseActivity
import com.gratus.petservice.view.interfaces.DeleteTelUserListernerAdapter
import com.gratus.petservice.viewModel.activity.TelUsersViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import java.util.*
import javax.inject.Inject


class TelUsersActivity : BaseActivity(), DeleteTelUserListernerAdapter {
    lateinit var activityTelAddDeleteBinding: ActivityTelAddDeleteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var telListAdapter: TelListAdapter

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    lateinit var telUsersViewModel: TelUsersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTelAddDeleteBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_tel_add_delete
        )
        telUsersViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TelUsersViewModel::class.java)
        activityTelAddDeleteBinding.telegramUserViewModel = telUsersViewModel
        activityTelAddDeleteBinding.lifecycleOwner = this
        setTelegramUp()
        telUsersViewModel.hitTelUser()
        telUsersViewModel.telUserResponse.observe(this, Observer {
            if (it.success) {
                if (it.output?.telUsers?.size!! > 0) {
                    telListAdapter.notifyDataSetChanged()
                } else {
                    DynamicToast.makeError(
                        this@TelUsersActivity,
                        "Telegram users not found"
                    ).show()
                }
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityTelAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@TelUsersActivity,
                        "Telegram users not found"
                    ).show()
                }
            }
        })
        telUsersViewModel.profileResponse.observe(this, Observer {
            if (it.success) {
                DynamicToast.makeSuccess(
                    this@TelUsersActivity,
                    it.message
                ).show()
                telUsersViewModel.hitTelUser()
            } else {
                if (it.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, activityTelAddDeleteBinding.parent)
                } else {
                    DynamicToast.makeError(
                        this@TelUsersActivity,
                        "Telegram users not deleted"
                    ).show()
                }
            }
        })
        activityTelAddDeleteBinding.backArrowImg.setOnClickListener { v -> onBackPressed() }
        activityTelAddDeleteBinding.addImg.setOnClickListener{ v->addTelegramUser()}
    }

    private fun addTelegramUser() {
        val layoutInflaterAndroid = LayoutInflater.from(this)
        val mView: View = layoutInflaterAndroid.inflate(R.layout.add_telegram_dialog, null)
        val alertDialogBuilderUserInput: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilderUserInput.setView(mView)
        val categoryEditText = mView.findViewById(R.id.userInputDialog) as EditText
        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton("Add", DialogInterface.OnClickListener { dialogBox, id ->
                if(categoryEditText.text.length>4) {
                    telUsersViewModel.hitTelAddteUser(Integer.parseInt(categoryEditText.text.toString()))
                }
                else{
                    DynamicToast.makeError(
                        this@TelUsersActivity,
                        "Enter valid telgram user Id"
                    ).show()
                }
            })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialogBox, id -> dialogBox.cancel() })

        val alertDialogAndroid: AlertDialog = alertDialogBuilderUserInput.create()
        alertDialogAndroid.show()
    }

    private fun updateReport() {
        telUsersViewModel.telUserResponse.value?.output?.telUsers?.let { telListAdapter.addItems(it) }
    }

    private fun setTelegramUp() {
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activityTelAddDeleteBinding.recView.layoutManager = mLayoutManager
        activityTelAddDeleteBinding.recView.itemAnimator = DefaultItemAnimator()
        activityTelAddDeleteBinding.recView.adapter = telListAdapter
        telListAdapter.setmListener(this)
    }

    override fun onItemClick(position: Int?) {
        if (position != null) {
            telUsersViewModel.hitTelDelteUser(position)
        }
    }
}