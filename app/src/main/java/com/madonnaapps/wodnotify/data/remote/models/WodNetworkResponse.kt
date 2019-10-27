package com.madonnaapps.wodnotify.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WodNetworkResponse (

    @Json(name = "items")
    val items: List<WodNetworkResponseItem>?

)