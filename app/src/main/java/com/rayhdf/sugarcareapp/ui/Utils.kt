package com.rayhdf.sugarcareapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateString: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    return zonedDateTime.format(formatter)
}