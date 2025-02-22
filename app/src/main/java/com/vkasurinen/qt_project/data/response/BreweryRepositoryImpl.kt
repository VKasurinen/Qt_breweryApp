package com.vkasurinen.qt_project.data.response

import com.vkasurinen.qt_project.data.mappers.toBreweryModel
import com.vkasurinen.qt_project.data.remote.BreweryApi
import com.vkasurinen.qt_project.domain.BreweryModel
import com.vkasurinen.qt_project.domain.BreweryRepository
import com.vkasurinen.qt_project.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BreweryRepositoryImpl(
    private val api: BreweryApi
) : BreweryRepository {

    override suspend fun fetchBreweriesFromIreland(): Flow<Resource<List<BreweryModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val breweriesFromApi = try {
                api.getBreweries("ireland")
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading breweries"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading breweries"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading breweries"))
                return@flow
            }

            val breweryModels = breweriesFromApi.map { it.toBreweryModel() }
            emit(Resource.Success(data = breweryModels))
            emit(Resource.Loading(false))
        }
    }
}