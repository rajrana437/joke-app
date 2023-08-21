package com.example.jokeapp.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.Model.Joke
import com.example.jokeapp.Service.JokeDatabase
import com.example.jokeapp.Service.JokeRepo
import com.example.jokeapp.Service.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JokeRepo
    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> = _joke
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        val jokeDao = JokeDatabase.getDatabase(application).jokeDao()
        val apiService = RetrofitInstance.provideService()
        repository = JokeRepo(jokeDao, apiService)
    }

    fun fetchJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val joke = repository.fetchJoke()
            if (joke == null) {
                _joke.postValue(repository.getLatestJokeFromDb())
            } else {
                _joke.postValue(joke)
            }
            _isLoading.postValue(false)
        }
    }
}
