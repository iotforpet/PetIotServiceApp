package com.gratus.petservice.util

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyValueFormatter : ValueFormatter() {
    private val mFormat: DecimalFormat
    override fun getFormattedValue(value: Float): String {
        return mFormat.format(value.toDouble())
    }

    init {
        mFormat = DecimalFormat("#")
    }
}
