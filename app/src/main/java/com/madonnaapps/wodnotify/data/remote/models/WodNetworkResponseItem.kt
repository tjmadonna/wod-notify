package com.madonnaapps.wodnotify.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WodNetworkResponseItem(

    @Json(name = "id")
    val id: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "author")
    val author: WodNetworkResponseAuthor?,

    @Json(name = "fullUrl")
    val relativeUrl: String?,

    @Json(name = "body")
    val description: String?

)