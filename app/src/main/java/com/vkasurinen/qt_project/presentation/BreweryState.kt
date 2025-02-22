package com.vkasurinen.qt_project.presentation

import com.vkasurinen.qt_project.domain.BreweryModel

data class BreweryState(
    val northernMostBrewery: BreweryModel? = null,
    val southernMostBrewery: BreweryModel? = null,
    val longestNameBrewery: BreweryModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)