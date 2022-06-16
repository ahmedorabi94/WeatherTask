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
import com.ahmedorabi.weatherapp.features.weather_details.framework.ApiRatesListDataSource
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
    fun provideInApiRatesListDataSource(apiService: ApiService): WeatherDataSource {
        return ApiRatesListDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideRatesRepository(ratesDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepository(ratesDataSource)
    }


    @Singleton
    @Provides
    fun provideUseCase(ratesRepository: WeatherRepository): GetCitiesUseCase {
        return GetCitiesUseCase(ratesRepository)
    }



    @Singleton
    @Provides
    fun provideInRoomLocalDataSource(rateDao: WeatherDao): RoomDataSource {
        return InRoomLocalDataSourceAddCity(rateDao)
    }


    @Singleton
    @Provides
    fun provideRoomRatesRepository(roomDataSource: RoomDataSource): RoomRepository {
        return RoomRepository(roomDataSource)
    }


    @Singleton
    @Provides
    fun provideAddRateUseCase(roomRepository: RoomRepository): AddCityUseCase {
        return AddCityUseCase(roomRepository)
    }

    @Singleton
    @Provides
    fun provideGetRatesRoomUseCase(roomRepository: RoomRepository): GetCitiesLocalUseCase {
        return GetCitiesLocalUseCase(roomRepository)
    }


}