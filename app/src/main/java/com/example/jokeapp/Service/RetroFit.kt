package com.example.jokeapp.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://icanhazdadjoke.com"
    private val retrofit by lazy {
        providesRetrofit()
    }

    private fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideService(): DataService {
        return retrofit.create(DataService::class.java)
    }
}
