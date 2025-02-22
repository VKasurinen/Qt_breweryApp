package com.vkasurinen.qt_project.di

import com.vkasurinen.qt_project.data.remote.BreweryApi
import com.vkasurinen.qt_project.data.response.BreweryRepositoryImpl
import com.vkasurinen.qt_project.domain.BreweryRepository
import com.vkasurinen.qt_project.presentation.BreweryViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openbrewerydb.org/v1/")
            .client(get())
            .build()
            .create(BreweryApi::class.java)
    }

    single<BreweryRepository> { BreweryRepositoryImpl(get()) }

    viewModel { BreweryViewModel(get()) }
}