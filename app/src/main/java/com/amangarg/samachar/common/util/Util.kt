package com.amangarg.samachar.common.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object Util {

    fun formatDateLegacy(input: String): String {

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val date = parser.parse(input)
        val formatter = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
        return formatter.format(date!!)
    }
}