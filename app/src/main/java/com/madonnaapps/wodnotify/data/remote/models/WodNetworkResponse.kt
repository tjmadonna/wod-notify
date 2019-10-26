package com.madonnaapps.wodnotify.data.remote.models

import com.squareup.moshi.Json

data class WodNetworkResponse (

    @Json(name = "items")
    val items: List<WodNetworkResponseItem>?

)