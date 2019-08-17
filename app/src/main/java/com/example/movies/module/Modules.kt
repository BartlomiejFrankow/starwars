package com.example.movies.module

import com.example.movies.ui.MainVM
import com.example.movies.api.Api
import com.example.movies.api.MoviesRepository
import com.example.movies.api.MoviesRepositoryImpl
import com.example.movies.ui.characters.CharactersViewModel
import com.example.movies.ui.movies.MoviesViewModel
import com.example.movies.ui.planets.PlanetsViewModel
import com.example.movies.ui.vehicles.VehiclesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val API_BASE_URL = "https://swapi.co/api/"
const val HEADER_CONTENT_TYPE = "Content-type: application/json"

val appModules = module {

    single { createWebService<Api>() }

    // Tells Koin how to create an instance of repository
    factory<MoviesRepository> { MoviesRepositoryImpl() }

    // Specific viewModel pattern to tell Koin how to build MainVM
    viewModel { MainVM() }
    viewModel { MoviesViewModel(moviesRepository = get()) }
    viewModel { CharactersViewModel() }
    viewModel { VehiclesViewModel() }
    viewModel { PlanetsViewModel() }

}

inline fun <reified T> createWebService(): T {
    val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder()
    client.addInterceptor(interceptor)

    val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client.build())
        .build()
    return retrofit.create(T::class.java)
}
