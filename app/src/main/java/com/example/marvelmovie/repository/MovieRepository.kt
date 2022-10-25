package com.example.marvelmovie.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.data.remote.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieRepository @Inject constructor(private val service: MovieService) {

    @Inject
    suspend fun getAllMovies() = service.getNextMovie()
}