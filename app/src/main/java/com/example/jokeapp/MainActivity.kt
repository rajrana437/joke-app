package com.example.jokeapp
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jokeapp.ViewModel.JokeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: JokeViewModel
    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)

        viewModel.fetchJoke()

        button.setOnClickListener {
            viewModel.fetchJoke()
        }

        viewModel.joke.observe(this, { joke ->
            textView.text = joke.joke
        })

        viewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) {
                progressBar.isShown()
            }
        })
    }
}
