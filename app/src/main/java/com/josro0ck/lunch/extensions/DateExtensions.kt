package com.josro0ck.lunch.extensions

import java.util.Date

val SECOND = 1000
val MINUTE = SECOND * 60
val HOUR = MINUTE * 60
val DAY = HOUR * 24

fun Date.addDays(i: Int): Date {
    return Date(this.time + (i * DAY))
}