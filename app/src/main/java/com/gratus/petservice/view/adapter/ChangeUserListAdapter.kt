package com.gratus.petservice.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gratus.petservice.databinding.ItemUserChangeBinding
import com.gratus.petservice.model.common.UserAdapterModel
import com.gratus.petservice.util.pref.AppPreferencesHelper
import com.gratus.petservice.view.activity.ChangeUsersActivity
import com.gratus.petservice.view.base.BaseViewHolder
import com.gratus.petservice.view.interfaces.DeleteUserListerner
import com.gratus.petservice.view.interfaces.DeleteUserListernerAdapter
import com.gratus.petservice.viewModel.adapter.ChangeUserItemViewModel
import javax.inject.Inject

class ChangeUserListAdapter @Inject constructor(private var users: ArrayList<UserAdapterModel>) :
        RecyclerView.Adapter<BaseViewHolder>() {
    var context: Context? = null
    private  var mListener: DeleteUserListernerAdapter? = null
    var prefs:AppPreferencesHelper? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                val itemUserBinding: ItemUserChangeBinding = ItemUserChangeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return  UserListViewHolder(itemUserBinding)
        }

    fun setmListener(mListeners: ChangeUsersActivity, prefss: AppPreferencesHelper?) {
        mListener = mListeners
        prefs = prefss
    }
    private inner class UserListViewHolder(itemUserBinding: ItemUserChangeBinding) :
            BaseViewHolder(itemUserBinding.root), DeleteUserListerner {
        private val mBinding: ItemUserChangeBinding = itemUserBinding
        private var userItemViewModel: ChangeUserItemViewModel? = null

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBind(position: Int) {
            userItemViewModel = ChangeUserItemViewModel(users[position],this)
            mBinding.changeUserItemViewModel = userItemViewModel
            if(prefs?.getUserId()==users[position].userID) {
                mBinding.calendar.visibility= View.VISIBLE
            }
            else{
                mBinding.calendar.visibility= View.INVISIBLE
            }
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