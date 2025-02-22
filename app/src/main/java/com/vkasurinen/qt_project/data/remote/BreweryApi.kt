package com.vkasurinen.qt_project.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApi {
    @GET("breweries")
    suspend fun getBreweries(@Query("by_country") country: String): List<BreweryDto>
}