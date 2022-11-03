package com.example.marvelmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.marvelmovie.data.entities.FollowingProduction
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.movieViewModle.MovieVM
import com.example.marvelmovie.repository.MovieRepository
import com.example.marvelmovie.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModelTest @Inject constructor(){

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()
    private lateinit var viewModel: MovieVM
    val myMarvelMovie = Movie("TestMovie",18,
        FollowingProduction(34,"next production","","21/08/2023","NextProduction","Movie")
        ,"my test movie","","","")

    val movieObserver : Observer<Movie> = mockk(relaxed = true)

    val repository: MovieRepository = mockk(relaxed = true){
        coEvery { getAllMovies() } returns MutableLiveData(myMarvelMovie)
    }

    @Before
    fun setUp(){


        viewModel = MovieVM(repository)
        viewModel.movieLiveData.observeForever(movieObserver)

    }

    @Test
    fun `should emit test movie`(){

        viewModel.movieLiveData.value = myMarvelMovie

        assert(viewModel.movieLiveData.value == myMarvelMovie)
    }




}

private infix fun <T, B> MockKStubScope<T, B>.returns(mutableLiveData: MutableLiveData<Movie>) {

}







