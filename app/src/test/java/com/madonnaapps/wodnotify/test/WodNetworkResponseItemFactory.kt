package com.madonnaapps.wodnotify.test

import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseAuthor
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseItem

object WodNetworkResponseItemFactory {

    fun makeWodNetworkResponseItem(date: String): WodNetworkResponseItem {
        return WodNetworkResponseItem(
            DataFactory.randomString(),
            date,
            makeWodNetworkResponseAuthor(),
            DataFactory.randomString(),
            DataFactory.randomString()
        )
    }

    fun makeWodNetworkResponseAuthor(): WodNetworkResponseAuthor {
        return WodNetworkResponseAuthor(
            DataFactory.randomString()
        )
    }

}