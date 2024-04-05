package com.devdcruz.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devdcruz.mymovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerMovie.adapter = MoviesAdapter(
            listOf(
                Movie("title 1", "URL 1"),
                Movie("title 2", "URL 2"),
                Movie("title 3", "URL 3"),
                Movie("title 4", "URL 4"),
                Movie("title 5", "URL 5"),
                Movie("title 6", "URL 6")
            )
        )
    }
}