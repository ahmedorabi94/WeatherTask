package com.ahmedorabi.weatherapp.features.add_city.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.core.domain.usecases.AddCityUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesLocalUseCase
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddCityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiCitiesObserver: Observer<List<City>>

    private lateinit var viewModel: AddCityViewModel


    @Mock
    private lateinit var useCase: GetCitiesLocalUseCase

    @Mock
    private lateinit var addCityUseCase: AddCityUseCase

    @Before
    fun setup() {
        viewModel = AddCityViewModel(useCase, addCityUseCase)
    }

    @Test
    fun shouldGetCitiesListSuccessResponse() {

        val list = ArrayList<City>()
        list.add(City(name = "london"))
        val flow = flow {
            emit(list)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke()

            viewModel.getCitiesResponseFlow()

            viewModel.citiesResponse.observeForever(apiCitiesObserver)

            Mockito.verify(useCase).invoke()

            Mockito.verify(apiCitiesObserver).onChanged(list)

            Assert.assertEquals(viewModel.citiesResponse.value, list)

            viewModel.citiesResponse.removeObserver(apiCitiesObserver)


        }
    }

    @Test
    fun test_add_city() {

        testCoroutineRule.runBlockingTest {

            viewModel.addCity(name = "london")

            Mockito.verify(addCityUseCase).invoke(City(name = "london"))

        }
    }
}