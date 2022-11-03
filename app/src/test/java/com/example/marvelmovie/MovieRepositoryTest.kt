package com.example.marvelmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelmovie.data.entities.FollowingProduction
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.data.remote.MovieService
import com.example.marvelmovie.repository.MovieRepository
import io.mockk.coEvery
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
import retrofit2.Response

class MovieRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()

    private val movieService: MovieService = mockk(relaxed = true)
    private lateinit var repository: MovieRepository
    val myMarvelMovie = Movie("TestMovie",18,
        FollowingProduction(34,"next production","","21/08/2023","NextProduction","Movie")
        ,"my test movie","","","")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        repository = MovieRepository(movieService)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `get next movie should return movie items`() = runBlocking{

       coEvery { movieService.getNextMovie() } returns Response.success(myMarvelMovie)

       val expectedMovie = repository.getAllMovies() // api data changes daily string literal wont work

        assertEquals(myMarvelMovie, expectedMovie.body())
    }

}


