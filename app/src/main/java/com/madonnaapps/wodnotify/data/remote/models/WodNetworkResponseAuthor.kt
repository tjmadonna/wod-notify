package com.madonnaapps.wodnotify.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WodNetworkResponseAuthor (

    @Json(name = "displayName")
    val name: String?

)