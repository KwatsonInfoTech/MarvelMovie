package com.example.marvelmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.marvelmovie.data.entities.FollowingProduction
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.data.remote.MovieService
import com.example.marvelmovie.injection.MovieModule
import com.example.marvelmovie.movieViewModle.MovieVM
import com.example.marvelmovie.repository.MovieRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import retrofit2.Response

class MovieRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()

    private val movieService: MovieService = mockk(relaxed = true)
    private val movieObserver: Movie = mockk(relaxed = true)
    private lateinit var repository: MovieRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        repository = MovieRepository(movieService)
        Dispatchers.setMain(UnconfinedTestDispatcher())


    }

    @Test
    fun `get next movie should return movie items`() = runBlocking{

        val expectedMovie = repository.getAllMovies() // api data changes daily string literal wont work

      //  assert(expectedMovie == movieService.getNextMovie())
        assertEquals(expectedMovie, movieService.getNextMovie())
    }

}


