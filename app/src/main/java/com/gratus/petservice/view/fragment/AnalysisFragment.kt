package com.gratus.petservice.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.gratus.petservice.R
import com.gratus.petservice.databinding.FragmentAnalysisBinding
import com.gratus.petservice.model.response.Graph
import com.gratus.petservice.util.MyValueFormatter
import com.gratus.petservice.util.constants.AppConstants
import com.gratus.petservice.view.base.BaseFragment
import com.gratus.petservice.viewModel.fragment.AnalysisViewModel
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class AnalysisFragment : BaseFragment() , OnChartValueSelectedListener {

    lateinit var fragmentAnalysisBinding: FragmentAnalysisBinding
    lateinit var mRootView: View

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var analysisViewModel: AnalysisViewModel
    var sensor:String? ="temp_1"
    fun AnalysisFragment() {}
    companion object {

        fun newInstance(): AnalysisFragment {
            var fragment = AnalysisFragment()
            val args = Bundle()
            fragment.arguments=args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAnalysisBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_analysis, container, false)
        mRootView = fragmentAnalysisBinding.getRoot()
        fragmentAnalysisBinding.analysisViewModel=analysisViewModel
        fragmentAnalysisBinding.lifecycleOwner=this
        getToday()
        fragmentAnalysisBinding.hourLayout.setOnClickListener{ v->getToday()}
        fragmentAnalysisBinding.weekLayout.setOnClickListener{ v->getWeek()}
//        val noz = arrayOf("Nozzle A", "Nozzle B", "Nozzle C", "Nozzle D")
        val gson = Gson()
        val json =getPrefs()!!.getSensorList()
        val dataList = gson.fromJson(json, Array<String>::class.java).asList()
        val data: ArrayList<String> = ArrayList()
        for(i in dataList){
            if(i.equals("temp_1"))
                data.add("Temperature")
            if(i.equals("WL_1"))
                data.add("Pet Water Level")
            if(i.equals("W_1"))
                data.add("Pet Food Level")
            if(i.equals("W_2"))
                data.add("Pet Weight")
        }
        val nozSpinnerAdapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, data)
        nozSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item) // Specify the layout to use when the list of choices appears
        fragmentAnalysisBinding.spinner.setAdapter(nozSpinnerAdapter)
        fragmentAnalysisBinding.spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int,
                                        id: Long) {
                sensor = fragmentAnalysisBinding.spinner.getSelectedItem().toString()
                if (sensor.equals("Temperature"))
                    sensor = "temp_1"
                if (sensor.equals("Pet Water Level"))
                    sensor = "WL_1"
                if (sensor.equals("Pet Food Level"))
                    sensor = "W_1"
                if (sensor.equals("Pet Weight"))
                    sensor = "W_2"
                getToday()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        fragmentAnalysisBinding.spinner1.setAdapter(nozSpinnerAdapter)
        fragmentAnalysisBinding.spinner1.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int,
                                        id: Long) {
                sensor = fragmentAnalysisBinding.spinner1.getSelectedItem().toString()
                if (sensor.equals("Temperature"))
                    sensor = "temp_1"
                if (sensor.equals("Pet Water Level"))
                    sensor = "WL_1"
                if (sensor.equals("Pet Food Level"))
                    sensor = "W_1"
                if (sensor.equals("Pet Weight"))
                    sensor = "W_2"
                getWeek()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        return mRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analysisViewModel = ViewModelProviders.of(this, viewModelFactory).get(AnalysisViewModel::class.java)
        analysisViewModel.analysisResponse.observe(activity!!) { analysisResponse ->
            if (analysisResponse.success) {
                set24Chart(analysisResponse.output)
            } else {
                if (analysisResponse.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, fragmentAnalysisBinding.parent)
                } else {
                    DynamicToast.makeError(activity!!, "No data Found")
                            .show()
                }
            }
        }
        analysisViewModel.analysisResponses.observe(activity!!) { analysisResponse ->
            if (analysisResponse.success) {
                setOneWeekChart(analysisResponse.output)
            } else {
                if (analysisResponse.status === AppConstants.NETWORK_CODE_EXP) {
                    showSnack(true, fragmentAnalysisBinding.parent)
                } else {
                    DynamicToast.makeError(activity!!, "No data Found")
                        .show()
                }
            }
        }
    }

    private fun setOneWeekChart(hour: ArrayList<Graph>) {
        val values = ArrayList<BarEntry>()
        val xAxisLabel = ArrayList<String>()
        var i = 0
        for (graphValue in hour) {
            values.add(BarEntry((i++).toFloat(), graphValue.data!!.toFloat()))
            xAxisLabel.add(graphValue.datetime.toString())
        }
        val hourChart: BarChart = fragmentAnalysisBinding.chart2
        hourChart.setDrawBarShadow(false)
        hourChart.setDrawValueAboveBar(true)
        hourChart.description.isEnabled = false
        hourChart.setMaxVisibleValueCount(60)
        hourChart.setPinchZoom(false)
        hourChart.setDrawGridBackground(false)
        hourChart.axisRight.isEnabled = false
        hourChart.animateY(2000)
        val xAxis = hourChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 7
        val xAxisFormatter: ValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return xAxisLabel[value.toInt()]
            }
        }
        xAxis.valueFormatter = xAxisFormatter
        val leftAxis = hourChart.axisLeft
        leftAxis.axisMinimum = 0f
        leftAxis.setDrawGridLines(false)
        val l = hourChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        val set1: BarDataSet
        if (hourChart.data != null &&
            hourChart.data.dataSetCount > 0
        ) {
            set1 = hourChart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            hourChart.data.notifyDataChanged()
            hourChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "Past One Week")
            set1.setColors(*ColorTemplate.JOYFUL_COLORS)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f
            data.setValueFormatter(MyValueFormatter())
            hourChart.data = data
            hourChart.data.notifyDataChanged()
            hourChart.notifyDataSetChanged()
        }
    }

    private fun getWeek() {
        analysisViewModel.hitAnalysisWeek(sensor.toString())
    }

    private fun getToday() {
        analysisViewModel.hitAnalysisToday(sensor.toString())
    }

    private fun set24Chart(hour: List<Graph>) {
        val values = ArrayList<BarEntry>()
        val xAxisLabel = ArrayList<String>()
        var i = 0
        for (graphValue in hour) {
            values.add(BarEntry((i++).toFloat(), graphValue.data!!.toFloat()))
            xAxisLabel.add(graphValue.datetime.toString())
        }
        val hourChart: BarChart = fragmentAnalysisBinding.chart1
        hourChart.setDrawBarShadow(false)
        hourChart.setDrawValueAboveBar(true)
        hourChart.description.isEnabled = false
        hourChart.setMaxVisibleValueCount(60)
        hourChart.setPinchZoom(false)
        hourChart.setDrawGridBackground(false)
        hourChart.axisRight.isEnabled = false
        hourChart.animateY(2000)
        val xAxis = hourChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 24
        val xAxisFormatter: ValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return xAxisLabel[value.toInt()]
            }
        }
        xAxis.valueFormatter = xAxisFormatter
        val leftAxis = hourChart.axisLeft
        leftAxis.axisMinimum = 0f
        leftAxis.setDrawGridLines(false)
        val l = hourChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        val set1: BarDataSet
        if (hourChart.data != null &&
            hourChart.data.dataSetCount > 0
        ) {
            set1 = hourChart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            hourChart.data.notifyDataChanged()
            hourChart.notifyDataSetChanged()
        } else {
                set1 = BarDataSet(values, "24 hours")
            set1.setColors(*ColorTemplate.JOYFUL_COLORS)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f
            data.setValueFormatter(MyValueFormatter())
            hourChart.data = data
            hourChart.data.notifyDataChanged()
            hourChart.notifyDataSetChanged()
        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected() {
        TODO("Not yet implemented")
    }
}