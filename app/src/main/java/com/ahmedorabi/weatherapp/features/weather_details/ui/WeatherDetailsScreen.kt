package com.ahmedorabi.weatherapp.features.weather_details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.features.weather_details.viewmodel.WeatherDetailsViewModel
import timber.log.Timber

@Composable
fun WeatherDetailsScreen(
    cityName : String= "",
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
) {

    val response by   viewModel.citiesResponse.observeAsState()

    val forecastResponse by   viewModel.forecastResponse.observeAsState()

    Timber.e("forecastResponse " + forecastResponse)
    LaunchedEffect(Unit) {
        viewModel.getCitiesResponseFlow(cityName)
        viewModel.getForecastResponseFlow(cityName)
    }
    Box(modifier = Modifier.fillMaxSize()) {

        when (response?.status) {
            Resource.Status.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center),
                    color = Color.Black
                )
            }
            Resource.Status.SUCCESS -> {
               // hideLoading()
                Timber.e(response?.data.toString())
              //  updateUI(userState.data!!)


                response?.data?.let {
                    val celsius = (it.main.temp - 273.15).toInt()

                    val url = "https://openweathermap.org/img/w/${it.weather[0].icon}.png"

                    if (!viewModel.isSaveHistoricalModel){
                        viewModel.addHistoricalModel(celsius,response?.data?.name?.lowercase() ?: "",response?.data?.weather?.firstOrNull()?.main ?: "")
                        viewModel.isSaveHistoricalModel = true
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title
                        Text(
                            text = response?.data?.name.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 28.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        // Weather Icon
//                        Icon(
//                            painter = painterResource(id = weatherIconRes),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(100.dp)
//                                .padding(30.dp)
//                        )

                        val painter = rememberAsyncImagePainter(model = url)
                        Image(
                            painter = painter,
                            contentDescription = "Image loaded using rememberImagePainter",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(30.dp)
                        )

                        // Description
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Description:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = response?.data!!.weather[0].description.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Temperature
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Temperature:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "$celsius \u2103",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Humidity
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Humidity:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = response?.data?.main?.humidity.toString() + " %",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Wind Speed
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wind Speed:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = response?.data?.wind?.speed.toString() + " km/h",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }
                    }
                }




            }
            Resource.Status.ERROR -> {
              //  hideLoading()
               // Toast.makeText(activity, userState.message, Toast.LENGTH_LONG).show()
            }

            else -> {}
        }




//        // Progress Bar
//        if (isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .size(60.dp)
//                    .align(Alignment.Center),
//                color = Color.Black
//            )
//        }
    }
}