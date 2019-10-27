package com.madonnaapps.wodnotify.test.extensions

import java.util.*

/**
 * Midnight of the current day
 */
val Calendar.midnightTimeOfCurrentDay: Date
    get() {
        val newCalendar = this.clone() as Calendar
        newCalendar.set(Calendar.HOUR_OF_DAY, 0)
        newCalendar.set(Calendar.MINUTE, 0)
        newCalendar.set(Calendar.SECOND, 0)
        newCalendar.set(Calendar.MILLISECOND, 0)
        return newCalendar.time
    }