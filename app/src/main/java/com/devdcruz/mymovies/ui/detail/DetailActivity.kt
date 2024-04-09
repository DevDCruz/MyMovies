package com.devdcruz.mymovies.ui.detail

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.devdcruz.mymovies.R
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
            appendInfo(R.string.original_language, movie.original_language)
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.vote_average.toString())

        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String) {
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? =
        when {
            SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
        }
}