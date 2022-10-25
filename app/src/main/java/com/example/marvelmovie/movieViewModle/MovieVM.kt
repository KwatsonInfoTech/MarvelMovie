package com.example.marvelmovie.movieViewModle

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.data.entities.FollowingProduction
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieVM @Inject constructor(private val repository: MovieRepository): ViewModel() {

    val movieLiveData = MutableLiveData<Movie>()


    fun fetchMovies(){

        viewModelScope.launch {
            val response = repository.getAllMovies()
            if(response.isSuccessful){
                movieLiveData.postValue(response.body())
            }else{
                //display error
                Log.i("error", "Movie data fetch unsuccessful")
            }
        }
    }


}