package com.josro0ck.lunch.extensions

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

val SECOND = 1000
val MINUTE = SECOND * 60
val HOUR = MINUTE * 60
val DAY = HOUR * 24


val US_DATE_TEMPLATE = "MM/dd/yyyy"

fun Date.addDays(i: Int): Date {
    return Date(this.time + (i * DAY))
}

fun String.toDate(pattern: String): Date {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = LocalDate.parse(this, formatter)
    return Date.from(
        date.atStartOfDay(
                ZoneId.of("America/Chicago")
            ).toInstant()
    )
}