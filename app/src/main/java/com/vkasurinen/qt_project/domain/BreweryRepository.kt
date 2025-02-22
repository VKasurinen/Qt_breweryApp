package com.vkasurinen.qt_project.domain

import com.vkasurinen.qt_project.Resource
import kotlinx.coroutines.flow.Flow

interface BreweryRepository {
    suspend fun fetchBreweriesFromIreland(): Flow<Resource<List<BreweryModel>>>
}