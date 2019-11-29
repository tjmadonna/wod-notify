package com.madonnaapps.wodnotify.test

import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponse
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseAuthor
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseItem

object WodNetworkResponseItemFactory {

    fun makeWodNetworkResponseItem(
        id: String = DataFactory.randomString(),
        title: String = DataFactory.randomString(),
        author: WodNetworkResponseAuthor = makeWodNetworkResponseAuthor(),
        relativeUrl: String = DataFactory.randomString(),
        description: String = DataFactory.randomString()
    ): WodNetworkResponseItem {
        return WodNetworkResponseItem(
            id,
            title,
            author,
            relativeUrl,
            description
        )
    }

    fun makeWodNetworkResponseItemList(count: Int) = mutableListOf<WodNetworkResponseItem>().apply {
        repeat(count) {
            add(makeWodNetworkResponseItem())
        }
    }

    fun makeWodNetworkResponseAuthor(
        name: String = DataFactory.randomString()
    ): WodNetworkResponseAuthor {
        return WodNetworkResponseAuthor(
            DataFactory.randomString()
        )
    }

    fun makeWodNetworkResponse(
        items: List<WodNetworkResponseItem>? = makeWodNetworkResponseItemList(5)
    ): WodNetworkResponse {
        return WodNetworkResponse(items)
    }

}