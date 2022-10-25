package com.example.marvelmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.data.remote.MovieService
import com.example.marvelmovie.movieViewModle.MovieVM
import com.example.marvelmovie.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()
    private lateinit var viewModel: MovieVM
    private val movieService: MovieService = mockk(relaxed = true)
    private val movieObserver: Observer<Movie> = mockk(relaxed = true)

   private var repository =  MovieRepository(movieService)


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
       viewModel = MovieVM(repository)
      //  repository = MovieRepository(movieService)
       // Dispatchers.setMain(UnconfinedTestDispatcher())


    }

    @Test
    fun `fetch movies should return live data of movies`() = runBlocking{

        repository.getAllMovies()
        //viewModel.movieLiveData.observeForever(movieObserver)
        // verify { movieObserver.onChanged(any()) }

    }





}