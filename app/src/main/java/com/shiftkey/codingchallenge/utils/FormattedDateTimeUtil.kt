package com.shiftkey.codingchallenge.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.shiftkey.codingchallenge.model.ShiftsItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormattedDateTimeUtil(val shiftsItem: ShiftsItem) {

    @RequiresApi(Build.VERSION_CODES.O)
    val formattedStartDate: String =
        LocalDateTime.parse(shiftsItem.startTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(
            DateTimeFormatter.ofPattern("MMM d, yyyy")
        )

    @RequiresApi(Build.VERSION_CODES.O)
    val formattedEndDate: String =
        LocalDateTime.parse(shiftsItem.endTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(
            DateTimeFormatter.ofPattern("MMM d, yyyy")
        )

    @RequiresApi(Build.VERSION_CODES.O)
    val startTimeToDisplayTime: String =
        LocalDateTime.parse(shiftsItem.startTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(
            DateTimeFormatter.ofPattern("h:mma")
        )

    @RequiresApi(Build.VERSION_CODES.O)
    val endTimeToDisplayTime: String =
        LocalDateTime.parse(shiftsItem.endTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(
            DateTimeFormatter.ofPattern("h:mma")
        )
}