package com.devdcruz.mymovies

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
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
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780${movie.backdrop_path}")
                .into(binding.ivDetailBackdrop)
            binding.tvDetailSummary.text = movie.overview
            bindDetailInfo(binding.tvDetailInfo, movie)
        }

    }

    private fun bindDetailInfo(tvDetailInfo: TextView, movie: Movie) {
        tvDetailInfo.text = buildSpannedString {
            bold { append("Original language: ") }
            appendLine(movie.original_language)
            bold { append("Original title: ") }
            appendLine(movie.original_title)
            bold { append("Release date: ") }
            appendLine(movie.release_date)
            bold { append("Popularity: ") }
            appendLine(movie.popularity.toString())
            bold { append("Vote average: ") }
            appendLine(movie.vote_average.toString())
        }
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? =
        when {
            SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
        }
}