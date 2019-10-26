package com.madonnaapps.wodnotify.common.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Attempts to parse text from a string to produce a Date. This method will return null if
 * the string is unable to be parsed and will not throw a ParseException
 * @param source optional text to be parsed to a date
 * @return optional date parsed from the given text
 */
fun SimpleDateFormat.parseOrNull(source: String?): Date? {

    if (source == null) return null

    return try {
        this.parse(source)
    } catch (e: ParseException) {
        null
    }
}