package com.devdcruz.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.devdcruz.mymovies.databinding.ActivityMainBinding
import com.devdcruz.mymovies.model.MovieDbClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList()) { movie ->
            Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
        }
        binding.recyclerMovie.adapter = moviesAdapter
        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.lisPopularMovies(apiKey)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }
    }
}