package br.com.iq.happycalendarandroid.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun StringToDate(dateInString: String, format: String): Date {

        val formatter = SimpleDateFormat(format)
        var date = Date()

        try {

            date = formatter.parse(dateInString)
            println(date)
            println(formatter.format(date))


        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    fun formatDateToString(date: Date, format: String): String{
        var dateToString: String = ""
        val formatter = SimpleDateFormat(format)
        dateToString = formatter.format(date).toString()
        return dateToString
    }

}