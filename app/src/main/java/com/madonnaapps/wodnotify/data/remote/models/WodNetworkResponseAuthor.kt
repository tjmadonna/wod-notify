package com.madonnaapps.wodnotify.data.remote.models

import com.squareup.moshi.Json

data class WodNetworkResponseAuthor (

    @Json(name = "displayName")
    val name: String?

)