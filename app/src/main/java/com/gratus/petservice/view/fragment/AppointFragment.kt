package com.gratus.petservice.view.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gratus.petservice.R
import com.gratus.petservice.databinding.FragmentAppointmentBinding
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.util.dateTime.DatatimeConversion
import com.gratus.petservice.view.base.BaseFragment
import com.gratus.petservice.viewModel.fragment.AppointViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import java.util.*
import javax.inject.Inject


class AppointFragment : BaseFragment() {

    lateinit var fragmentAppointmentBinding: FragmentAppointmentBinding
    lateinit var mRootView: View

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var appointViewModel: AppointViewModel

    var datatimeConversion: DatatimeConversion = DatatimeConversion()

    fun AppointFragment() {}
    companion object {

        fun newInstance(): AppointFragment {
            var fragment = AppointFragment()
            val args = Bundle()
            fragment.arguments=args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAppointmentBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_appointment, container, false)
        mRootView = fragmentAppointmentBinding.getRoot()
        fragmentAppointmentBinding.appointViewModel=appointViewModel
        fragmentAppointmentBinding.lifecycleOwner=this
        fragmentAppointmentBinding.scServiceTv.setOnClickListener { v -> scServiceTv() }
        fragmentAppointmentBinding.brServiceTv.setOnClickListener { v -> brServiceTv() }
        fragmentAppointmentBinding.dateTv.setOnClickListener { v -> dateTv() }
        fragmentAppointmentBinding.timeTv.setOnClickListener { v -> timeTv() }
        fragmentAppointmentBinding.bookLayout.setOnClickListener { v -> bookLayout() }
        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appointViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppointViewModel::class.java)
        appointViewModel.loginResponses.observe(activity!!) { loginResponses ->
            if (loginResponses.success) {
                DynamicToast.makeSuccess(activity!!, loginResponses.message)
                        .show()
                fragmentAppointmentBinding.scServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0,0, 0);
                fragmentAppointmentBinding.brServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                appointViewModel.appointRequest.type_of_service = ""
                appointViewModel.appointRequest.time_stamp = ""
                appointViewModel.appointRequest.customer_voice = ""
                fragmentAppointmentBinding.dateTv.setText("")
                fragmentAppointmentBinding.dateTv.setHint("dd-mm-yyyy")
                fragmentAppointmentBinding.timeTv.setText("")
                fragmentAppointmentBinding.timeTv.setHint("HH:MM:SS")
                fragmentAppointmentBinding.customerVTV.setText("")
            } else {
                if (loginResponses.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, fragmentAppointmentBinding.parent)
                } else {
                    DynamicToast.makeError(activity!!, loginResponses.message)
                            .show()
                }
            }
        }
    }

    fun scServiceTv() {
        fragmentAppointmentBinding.scServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_green_tick, 0);
        fragmentAppointmentBinding.brServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        appointViewModel.appointRequest.type_of_service = fragmentAppointmentBinding.scServiceTv.text.toString()
    }

    fun brServiceTv() {
        fragmentAppointmentBinding.scServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        fragmentAppointmentBinding.brServiceTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.circle_green_tick, 0);
        appointViewModel.appointRequest.type_of_service = fragmentAppointmentBinding.brServiceTv.text.toString()
    }

    fun dateTv() {
      var mYear:Int = 0
      var mMonth:Int = 0
      var mDay:Int = 0
        val c: Calendar = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = activity?.let {
            DatePickerDialog(it,
                    OnDateSetListener { view, year, monthOfYear, dayOfMonth -> fragmentAppointmentBinding.dateTv.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) }, mYear, mMonth, mDay)
        }
        datePickerDialog?.show()
    }

    fun timeTv() {
        var mHour:Int = 0
        var mMinute:Int = 0

        val c = Calendar.getInstance()
         mHour = c[Calendar.HOUR_OF_DAY]
         mMinute = c[Calendar.MINUTE]


        // Launch Time Picker Dialog
        val timePickerDialog = TimePickerDialog(activity,
                OnTimeSetListener { view, hourOfDay, minute -> fragmentAppointmentBinding.timeTv.setText("$hourOfDay:$minute:00") }, mHour, mMinute, true)
        timePickerDialog.show()
    }

  fun bookLayout(){
      if(appointViewModel.appointRequest.type_of_service.isNullOrEmpty()){
          activity?.let {
              DynamicToast.makeError(it, "Please click any one service")
                      .show()
          }
      }
      else if(fragmentAppointmentBinding.dateTv.text.toString().isNullOrBlank()){
          activity?.let {
              DynamicToast.makeError(it, "Please select a date")
                      .show()
          }
      }
      else if(fragmentAppointmentBinding.timeTv.text.toString().isNullOrBlank()){
                  activity?.let {
                      DynamicToast.makeError(it, "Please select a time")
                              .show()
                  }
      }
      else{
          appointViewModel.appointRequest.email = getPrefs()?.getUsername()
          appointViewModel.appointRequest.time_stamp = datatimeConversion.appoint(fragmentAppointmentBinding.dateTv.text.toString() + " " + fragmentAppointmentBinding.timeTv.text.toString())
          appointViewModel.hitAppoint()
      }
  }
}