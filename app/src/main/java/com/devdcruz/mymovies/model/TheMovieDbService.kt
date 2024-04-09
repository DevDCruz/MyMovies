package com.devdcruz.mymovies.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {
    @GET("popular")
    suspend fun lisPopularMovies(
        @Query("api_key") apiKey : String,
        @Query("region") region : String
    ): MovieDbResult
}