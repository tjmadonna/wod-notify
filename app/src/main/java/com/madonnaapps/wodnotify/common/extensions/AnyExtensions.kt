package com.madonnaapps.wodnotify.common.extensions

/**
 * Tag name used when logging to the console
 */
val Any.logTag: String
    get() = javaClass.simpleName