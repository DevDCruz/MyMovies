package com.devdcruz.mymovies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devdcruz.mymovies.databinding.ViewMovieItemBinding
import com.devdcruz.mymovies.model.Movie

class MoviesAdapter(
    var movies: List<Movie>,
    private val movieClickedListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvItemMovie.text = movie.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(binding.ivItemMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binging =
            ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binging)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { movieClickedListener(movie) }
    }
}