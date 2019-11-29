package com.madonnaapps.wodnotify.test

import java.util.*
import kotlin.random.Random
import kotlin.random.nextLong

internal object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomDate(): Date {
        return Date(Random.nextLong(0..Long.MAX_VALUE))
    }

}