package com.example.jokeapp.Service

import com.example.jokeapp.Model.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DataService {
    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getJoke(): Response<Joke>
}
