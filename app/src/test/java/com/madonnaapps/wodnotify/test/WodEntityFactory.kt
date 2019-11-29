package com.madonnaapps.wodnotify.test

import com.madonnaapps.wodnotify.data.local.entities.WodEntity

object WodEntityFactory {

    fun makeWodEntity() = WodEntity(
        DataFactory.randomString(),
        DataFactory.randomDate(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString(),
        DataFactory.randomString()
    )

    fun makeWodEntityList(count: Int) = mutableListOf<WodEntity>().apply {
        repeat(count) {
            add(makeWodEntity())
        }
    }

}