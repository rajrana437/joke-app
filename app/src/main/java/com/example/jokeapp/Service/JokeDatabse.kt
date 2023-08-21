package com.example.jokeapp.Service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokeapp.Model.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

    companion object {
        @Volatile
        private var INSTANCE: JokeDatabase? = null

        fun getDatabase(context: Context): JokeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JokeDatabase::class.java,
                    "joke_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
