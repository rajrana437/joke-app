package com.example.jokeapp.Service

import com.example.jokeapp.Model.Joke

class JokeRepo(private val jokeDao: JokeDao, private val apiService: DataService) {

    suspend fun fetchJoke(): Joke? {
        try {
            val response = apiService.getJoke()
            if (response.isSuccessful) {
                val joke = response.body()
                joke?.let {
                    jokeDao.insertJoke(it)
                }
                return joke
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    suspend fun getLatestJokeFromDb(): Joke? {
        return jokeDao.getLatestJoke()
    }
}
