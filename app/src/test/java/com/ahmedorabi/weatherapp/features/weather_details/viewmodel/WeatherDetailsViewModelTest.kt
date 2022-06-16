package com.ahmedorabi.weatherapp.features.weather_details.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.Clouds
import com.ahmedorabi.weatherapp.core.domain.model.Coord
import com.ahmedorabi.weatherapp.core.domain.model.Main
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
    private lateinit var apiRatesObserver: Observer<Resource<WeatherResponse>>

    private lateinit var viewModel: WeatherDetailsViewModel


    @Mock
    private lateinit var useCase: GetCitiesUseCase

    @Mock
    private lateinit var addHistoricalModelUseCase: AddHistoricalModelUseCase

    @Before
    fun setup() {
        viewModel = WeatherDetailsViewModel(useCase,addHistoricalModelUseCase)
    }

    @Test
    fun shouldGetRatesListSuccessResponse() {

        val rateResponse = mock(WeatherResponse::class.java)

        val result1 = Resource.success(rateResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke("london")

            viewModel.getRatesResponseFlow("london")

            viewModel.ratesResponse.observeForever(apiRatesObserver)

            Mockito.verify(useCase).invoke("london")

            Mockito.verify(apiRatesObserver).onChanged(Resource.success(rateResponse))

            assertEquals(viewModel.ratesResponse.value, result1)

            viewModel.ratesResponse.removeObserver(apiRatesObserver)


        }


    }

}