package com.ahmedorabi.weatherapp.features.add_city.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmedorabi.weatherapp.features.utils.NavigationItem
import com.ahmedorabi.weatherapp.features.add_city.viewmodel.AddCityViewModel

@Preview
@Composable
fun AddCityScreen(
    navController: NavHostController? = null,
    viewModel: AddCityViewModel = hiltViewModel(),
    cities: List<String> = emptyList(), // A list of cities to display
    onAddCityClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {

    val citiesList by  viewModel.allCities.observeAsState()
    val showDialog = remember { mutableStateOf(false) }


    if (showDialog.value){
        AddCityDialog(showDialog = showDialog)
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // RecyclerView equivalent: LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top
        ) {
            items(citiesList.orEmpty()) { city ->
                CityCard(cityName = city.name , onCardClick = {
                    navController?.navigate(NavigationItem.WeatherDetailsScreen.route + "/${city.name}")
                }, onInfoClick = {
                    navController?.navigate(NavigationItem.HistoricalScreen.route + "/${city.name}")
                } , onTitleClick = {})
            }
        }

        // Add City Button
        Button(
            onClick = {

                showDialog.value = true

             // navController?.navigate(NavigationItem.AddCityDialog.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Add,
                contentDescription = "Add City Icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add City",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun CityItem(cityName: String) {
    Text(
        text = cityName,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}