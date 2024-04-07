package com.devdcruz.mymovies

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.devdcruz.mymovies.databinding.ActivityDetailBinding
import com.devdcruz.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movie = intent.parcelable<Movie>(EXTRA_MOVIE)
        if (movie != null) {
            binding.tvDetailTitle.text = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780${movie.backdrop_path}")
                .into(binding.ivDetailBackdrop)
        }

    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? =
        when {
            SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
        }
}