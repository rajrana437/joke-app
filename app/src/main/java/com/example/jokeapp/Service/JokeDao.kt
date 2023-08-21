package com.example.jokeapp.Service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jokeapp.Model.Joke

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: Joke)

    @Query("SELECT * FROM joke_table ORDER BY id DESC LIMIT 1")
    suspend fun getLatestJoke(): Joke?
}
