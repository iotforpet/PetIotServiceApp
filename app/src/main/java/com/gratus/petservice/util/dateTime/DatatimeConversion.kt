package com.gratus.petservice.util.dateTime

import java.text.SimpleDateFormat
import java.util.*


class DatatimeConversion constructor(){
    fun appoint(date:String): String {
        var sdf: SimpleDateFormat = SimpleDateFormat("dd-mm-yyyy HH:MM:SS")
        var sdf1 = SimpleDateFormat("yyyy-mm-dd HH:MM:SS")
        var parseDate: Date = sdf.parse(date)
        var output = sdf1.format(parseDate)
        return output
    }
}