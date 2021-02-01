package com.gratus.petservice.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gratus.petservice.databinding.ItemUserBinding
import com.gratus.petservice.model.common.UserAdapterModel
import com.gratus.petservice.model.response.Users
import com.gratus.petservice.view.activity.UsersActivity
import com.gratus.petservice.view.base.BaseViewHolder
import com.gratus.petservice.view.interfaces.DeleteUserListerner
import com.gratus.petservice.view.interfaces.DeleteUserListernerAdapter
import com.gratus.petservice.viewModel.adapter.UserItemViewModel
import javax.inject.Inject

class UserListAdapter @Inject constructor(private var users: ArrayList<UserAdapterModel>) :
        RecyclerView.Adapter<BaseViewHolder>() {
    var context: Context? = null
    private  var mListener: DeleteUserListernerAdapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                val itemUserBinding: ItemUserBinding = ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return  UserListViewHolder(itemUserBinding)
        }

    fun setmListener(mListeners: UsersActivity) {
        mListener = mListeners
    }
    private inner class UserListViewHolder(itemUserBinding: ItemUserBinding) :
            BaseViewHolder(itemUserBinding.root), DeleteUserListerner {
        private val mBinding: ItemUserBinding = itemUserBinding
        private var userItemViewModel: UserItemViewModel? = null

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBind(position: Int) {
            userItemViewModel = UserItemViewModel(users[position],this)
            mBinding.userItemViewModel = userItemViewModel
        }

        override fun onItemClick() {
            mListener?.onItemClick(users.get(adapterPosition))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        context = holder.itemView.getContext();
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    fun addItems(userss: ArrayList<UserAdapterModel>) {
        users.clear()
        if (userss != null) {
            if (userss.size > 0) {
                users.addAll(userss)
                notifyDataSetChanged()
            }
        }
    }

    fun clearItems() {
        users.clear()
    }
}