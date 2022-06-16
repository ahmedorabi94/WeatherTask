package com.ahmedorabi.weatherapp.features.weather_details.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.ahmedorabi.weatherapp.core.data.api.ApiService
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
 class ApiRatesListDataSourceTest{
     @get:Rule
     val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

     @get:Rule
     val testCoroutineRule = TestCoroutineRule()


     @Mock
     lateinit var apiService: ApiService


     private lateinit var apiRatesListDataSource: ApiRatesListDataSource


     @Before
     fun setup() {

         apiRatesListDataSource = ApiRatesListDataSource(apiService)

     }


    @Test
    fun shouldGetRatesSuccessResponse() {


        val rateResponse = Mockito.mock(WeatherResponse::class.java)
        val result1 = Resource.success(rateResponse)

        runBlocking {

            Mockito.doReturn(rateResponse)
                .`when`(apiService)
                .getWeatherResponseAsync("london")

            val response = apiRatesListDataSource.getWeatherResponse("london").drop(1).first()

            Assert.assertEquals(response, result1)

        }
    }


    @Test
    fun shouldGetListRatesFailureResponse() {

        val result1 = Resource.error<WeatherResponse>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getWeatherResponseAsync("london")).willAnswer {
                throw IOException("Ooops")
            }

            val response = apiRatesListDataSource.getWeatherResponse("london").drop(1).first()

            Assert.assertEquals(response, result1)


        }
    }
 }