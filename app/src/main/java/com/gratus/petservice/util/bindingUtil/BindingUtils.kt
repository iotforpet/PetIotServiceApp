package com.gratus.petservice.util.bindingUtil


import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.gratus.petservice.R
import com.gratus.petservice.model.common.UserAdapterModel
import com.gratus.petservice.model.response.Users
import com.gratus.petservice.util.constants.AppConstants.Companion.FEMALE
import com.gratus.petservice.util.constants.AppConstants.Companion.MALE
import com.gratus.petservice.view.adapter.ChangeUserListAdapter
import com.gratus.petservice.view.adapter.TelListAdapter
import com.gratus.petservice.view.adapter.UserListAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("visibilityButton")
    fun setVisibilityButton(button: MaterialButton, visibility: Boolean) {
        if (visibility) {
            button.visibility = View.VISIBLE
        } else {
            button.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("visibilityProgressBar")
    fun setVisibilityProgressBar(
            progressBar: ProgressBar,
            visibility: Boolean
    ) {
        if (visibility) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("errorText")
    fun setErrorMessage(
            textIputLayout: TextInputLayout,
            strOrResId: Any?
    ) {
        if (strOrResId is Int) {
            textIputLayout.error = textIputLayout.context.getString((strOrResId as Int?)!!)
        } else {
            textIputLayout.error = strOrResId as String?
        }
    }

    @JvmStatic
    @BindingAdapter("textChangedListener")
    fun bindTextWatcher(editText: TextInputEditText, textWatcher: TextWatcher?) {
        editText.addTextChangedListener(textWatcher)
    }
    @JvmStatic
    @BindingAdapter("textenabled")
    fun setTextEnabled(editText: TextInputEditText, enabled: Boolean) {
        editText.isEnabled = enabled
    }
    @JvmStatic
    @BindingAdapter("visibilityRelative")
    fun setVisibilityRelative(relativeLayout: RelativeLayout, visibility: Boolean) {
        if (visibility) {
            relativeLayout.visibility = View.VISIBLE
        } else {
            relativeLayout.visibility = View.GONE
        }
    }
    @JvmStatic
    @BindingAdapter("srcMale")
    fun setMaleDrawable(view: ImageView, gender: String?) {
        val context = view.context
        if (gender != null) {
            if (gender.equals(MALE, ignoreCase = true)) {
                view.background = context.getDrawable(R.drawable.buttonchecked)
                view.isEnabled = false
            } else {
                view.background = context.getDrawable(R.drawable.buttonunchecked)
                view.isEnabled = true
            }
        } else {
            view.background = context.getDrawable(R.drawable.buttonunchecked)
            view.isEnabled = true
        }
    }
    @JvmStatic
    @BindingAdapter("srcFemale")
    fun setFemaleDrawable(view: ImageView, gender: String?) {
        val context = view.context
        if (gender != null) {
            if (gender.equals(FEMALE, ignoreCase = true)) {
                view.background = context.getDrawable(R.drawable.buttonchecked)
                view.isEnabled = false
            } else {
                view.background = context.getDrawable(R.drawable.buttonunchecked)
                view.isEnabled = true
            }
        } else {
            view.background = context.getDrawable(R.drawable.buttonunchecked)
            view.isEnabled = true
        }
    }
    @JvmStatic
    @BindingAdapter("telListAdapter")
    fun addBlogItems(recyclerView: RecyclerView, telUsers: ArrayList<Int>?) {
        val adapter: TelListAdapter? = recyclerView.adapter as TelListAdapter?
        if (adapter != null) {
            if (telUsers != null) {
                if (telUsers.size > 0) {
                    adapter.clearItems()
                }
            }
            if (telUsers != null) {
                if (telUsers.size > 0) {
                    adapter.addItems(telUsers)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("userListAdapter")
    fun addUserlogItems(recyclerView: RecyclerView, users: ArrayList<UserAdapterModel>?) {
        val adapter: UserListAdapter? = recyclerView.adapter as UserListAdapter?
        if (adapter != null) {
            if (users != null) {
                if (users.size > 0) {
                    adapter.clearItems()
                }
            }
            if (users != null) {
                if (users.size > 0) {
                    adapter.addItems(users)
                }
            }
        }
    }
    @JvmStatic
    @BindingAdapter("changeUserListAdapter")
    fun changeUserlogItems(recyclerView: RecyclerView, users: ArrayList<UserAdapterModel>?) {
        val adapter: ChangeUserListAdapter? = recyclerView.adapter as ChangeUserListAdapter?
        if (adapter != null) {
            if (users != null) {
                if (users.size > 0) {
                    adapter.clearItems()
                }
            }
            if (users != null) {
                if (users.size > 0) {
                    adapter.addItems(users)
                }
            }
        }
    }
}