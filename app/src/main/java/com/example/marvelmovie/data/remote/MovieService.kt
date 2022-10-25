package com.example.marvelmovie.data.remote

import com.example.marvelmovie.data.entities.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("api")
    suspend fun getNextMovie(): Response<Movie>
}