package com.manojrai.androidtst.utils.common

import android.text.format.DateFormat
import java.util.*

object DateUtils {

    fun getDate(time: Long): String {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time * 1000
        return DateFormat.format("dd MMM yyyy", cal).toString()
    }

}