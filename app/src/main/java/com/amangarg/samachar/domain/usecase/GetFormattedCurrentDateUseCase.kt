package com.amangarg.samachar.domain.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetFormattedCurrentDateUseCase {

    operator fun invoke(): String {
        val date = Date()
        val formatter = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
        return formatter.format(date)
    }
}