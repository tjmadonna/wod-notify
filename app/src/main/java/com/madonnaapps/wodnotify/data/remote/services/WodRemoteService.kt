package com.madonnaapps.wodnotify.data.remote.services

import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponse
import retrofit2.http.GET

interface WodRemoteService {

    @GET("wods?format=json")
    suspend fun getWods(): WodNetworkResponse

}