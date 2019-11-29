package com.madonnaapps.wodnotify.data.remote.mappers

import com.madonnaapps.wodnotify.common.extensions.parseOrNull
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseItem
import java.text.SimpleDateFormat
import java.util.*

interface WodNetworkResponseItemMapper {

    fun mapToWodEntity(responseItem: WodNetworkResponseItem): WodEntity?

}

class WodNetworkResponseItemMapperImpl(
    private val dateFormats: Array<SimpleDateFormat>,
    private val baseUrl: String
): WodNetworkResponseItemMapper {

    override fun mapToWodEntity(responseItem: WodNetworkResponseItem): WodEntity? {
        return WodEntity(
            responseItem.id ?: return null,
            convertTextToDate(responseItem.title) ?: return null,
            responseItem.title ?: return null,
            responseItem.author?.name ?: return null,
            concatenateUrlWithBaseUrl(responseItem.relativeUrl) ?: return null,
            responseItem.description ?: return null
        )

    }

    private fun convertTextToDate(text: String?): Date? {
        dateFormats.forEach { dateFormat ->
            dateFormat.parseOrNull(text)?.let { return it }
        }
        return null
    }

    private fun concatenateUrlWithBaseUrl(url: String?): String? {
        return url?.let {
            "$baseUrl$url"
        } ?: run {
            null
        }
    }

}