package com.madonnaapps.wodnotify.data.remote.services

import com.madonnaapps.wodnotify.BuildConfig
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponse
import retrofit2.http.GET

interface WodRemoteService {

    @GET(BuildConfig.WODS_URL_PATH)
    suspend fun getWods(): WodNetworkResponse

}