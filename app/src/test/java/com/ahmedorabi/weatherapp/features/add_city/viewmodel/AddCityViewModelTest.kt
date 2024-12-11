package com.ahmedorabi.weatherapp.features.add_city.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.example.core.domain.model.City
import com.example.core.domain.usecases.AddCityUseCase
import com.example.core.domain.usecases.GetCitiesLocalUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun test_add_city() {

        testCoroutineRule.runBlockingTest {

            viewModel.addCity(name = "london")

            Mockito.verify(addCityUseCase).invoke(City(name = "london"))

        }
    }
}