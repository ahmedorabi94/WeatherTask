package com.ahmedorabi.weatherapp.features.weather_details.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import com.ahmedorabi.weatherapp.core.domain.usecases.AddHistoricalModelUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiCitiesObserver: Observer<Resource<WeatherResponse>>

    private lateinit var viewModel: WeatherDetailsViewModel


    @Mock
    private lateinit var useCase: GetCitiesUseCase

    @Mock
    private lateinit var addHistoricalModelUseCase: AddHistoricalModelUseCase

    @Before
    fun setup() {
        viewModel = WeatherDetailsViewModel(useCase, addHistoricalModelUseCase)
    }

    @Test
    fun shouldGetCitiesListSuccessResponse() {

        val weatherResponse = mock(WeatherResponse::class.java)

        val result1 = Resource.success(weatherResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke("london")

            viewModel.getCitiesResponseFlow("london")

            viewModel.citiesResponse.observeForever(apiCitiesObserver)

            Mockito.verify(useCase).invoke("london")

            Mockito.verify(apiCitiesObserver).onChanged(Resource.success(weatherResponse))

            assertEquals(viewModel.citiesResponse.value, result1)

            viewModel.citiesResponse.removeObserver(apiCitiesObserver)


        }


    }

    @Test
    fun test_addHistoricalModel() {

        testCoroutineRule.runBlockingTest {

            val historicalModel = HistoricalModel(name = "", dateTime = "", temp = 0, desc = "")
            viewModel.addHistoricalModel(historicalModel)

            Mockito.verify(addHistoricalModelUseCase).invoke(historicalModel)

        }
    }

}