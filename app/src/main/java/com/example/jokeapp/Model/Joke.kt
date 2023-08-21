package com.example.jokeapp.Model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_table")
data class Joke(
    @PrimaryKey val id: String,
    val joke: String,
    val status: Int
)
