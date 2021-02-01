package com.gratus.petservice.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gratus.petservice.databinding.ItemTelegramBinding
import com.gratus.petservice.view.activity.TelUsersActivity
import com.gratus.petservice.view.base.BaseViewHolder
import com.gratus.petservice.view.interfaces.DeleteTelUserListerner
import com.gratus.petservice.view.interfaces.DeleteTelUserListernerAdapter
import com.gratus.petservice.viewModel.adapter.TelUserItemViewModel
import javax.inject.Inject

class TelListAdapter @Inject constructor(private var telusers: ArrayList<Int>) :
        RecyclerView.Adapter<BaseViewHolder>() {
    var context: Context? = null
    private  var mListener: DeleteTelUserListernerAdapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                val itemDtcBinding: ItemTelegramBinding = ItemTelegramBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return  TelUserListViewHolder(itemDtcBinding)
        }

    fun setmListener(mListeners: TelUsersActivity) {
        mListener = mListeners
    }
    private inner class TelUserListViewHolder(itemDtcBinding: ItemTelegramBinding) :
            BaseViewHolder(itemDtcBinding.root),DeleteTelUserListerner {
        private val mBinding: ItemTelegramBinding = itemDtcBinding
        private var telUserItemViewModel: TelUserItemViewModel? = null

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBind(position: Int) {
            telUserItemViewModel = TelUserItemViewModel(telusers[position],this)
            mBinding.telUserItemViewModel = telUserItemViewModel
        }

        override fun onItemClick() {
            mListener?.onItemClick(telusers.get(adapterPosition))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        context = holder.itemView.getContext();
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return telusers.size
    }


    fun addItems(telUserss: ArrayList<Int>) {
        telusers.clear()
        if (telUserss != null) {
            if (telUserss.size > 0) {
                telusers.addAll(telUserss)
                notifyDataSetChanged()
            }
        }
    }

    fun clearItems() {
        telusers.clear()
    }
}