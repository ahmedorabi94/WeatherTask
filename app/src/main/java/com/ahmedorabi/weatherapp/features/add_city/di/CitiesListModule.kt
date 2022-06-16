package com.ahmedorabi.weatherapp.features.add_city.di


import com.ahmedorabi.weatherapp.core.data.api.ApiService
import com.ahmedorabi.weatherapp.core.db.WeatherDao
import com.ahmedorabi.weatherapp.core.domain.usecases.AddCityUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesLocalUseCase
import com.ahmedorabi.weatherapp.core.domain.usecases.GetCitiesUseCase
import com.ahmedorabi.weatherapp.core.repo.RoomDataSource
import com.ahmedorabi.weatherapp.core.repo.RoomRepository
import com.ahmedorabi.weatherapp.core.repo.WeatherDataSource
import com.ahmedorabi.weatherapp.core.repo.WeatherRepository
import com.ahmedorabi.weatherapp.features.add_city.framework.InRoomLocalDataSourceAddCity
import com.ahmedorabi.weatherapp.features.weather_details.framework.ApiCitiesListDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CitiesListModule {

    @Singleton
    @Provides
    fun provideInApiCitiesListDataSource(apiService: ApiService): WeatherDataSource {
        return ApiCitiesListDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideCitiesRepository(citiesDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepository(citiesDataSource)
    }


    @Singleton
    @Provides
    fun provideUseCase(citiesRepository: WeatherRepository): GetCitiesUseCase {
        return GetCitiesUseCase(citiesRepository)
    }



    @Singleton
    @Provides
    fun provideInRoomLocalDataSource(cityDao: WeatherDao): RoomDataSource {
        return InRoomLocalDataSourceAddCity(cityDao)
    }


    @Singleton
    @Provides
    fun provideRoomCitiesRepository(roomDataSource: RoomDataSource): RoomRepository {
        return RoomRepository(roomDataSource)
    }


    @Singleton
    @Provides
    fun provideAddCityUseCase(roomRepository: RoomRepository): AddCityUseCase {
        return AddCityUseCase(roomRepository)
    }

    @Singleton
    @Provides
    fun provideGetCitiesRoomUseCase(roomRepository: RoomRepository): GetCitiesLocalUseCase {
        return GetCitiesLocalUseCase(roomRepository)
    }


}