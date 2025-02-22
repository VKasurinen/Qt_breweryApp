package com.vkasurinen.qt_project.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkasurinen.qt_project.Resource
import com.vkasurinen.qt_project.domain.BreweryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreweryViewModel(
    private val breweryRepository: BreweryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BreweryState())
    val state: StateFlow<BreweryState> = _state.asStateFlow()

    init {
        fetchBreweries()
    }

    fun fetchBreweries() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            breweryRepository.fetchBreweriesFromIreland().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { breweries ->
                            val northernMostBrewery = breweries.maxByOrNull { it.latitude ?: Double.MIN_VALUE }
                            val southernMostBrewery = breweries.minByOrNull { it.latitude ?: Double.MAX_VALUE }
                            val longestNameBrewery = breweries.maxByOrNull { it.name.length }

                            _state.update {
                                it.copy(
                                    northernMostBrewery = northernMostBrewery,
                                    southernMostBrewery = southernMostBrewery,
                                    longestNameBrewery = longestNameBrewery,
                                    isLoading = false
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = result.isLoading) }
                    }
                }
            }
        }
    }
}