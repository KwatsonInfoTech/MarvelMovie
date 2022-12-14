package com.example.marvelmovie

import com.example.marvelmovie.data.remote.MovieService
import com.google.gson.Gson
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: MovieService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(MovieService::class.java)
    }


    @Test
    fun `get next movie api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getNextMovie()
            val request = mockWebServer.takeRequest()
            Assert.assertEquals("/api",request.path)
            Assert.assertEquals(true, response.isSuccessful)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}