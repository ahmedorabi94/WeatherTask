package com.ahmedorabi.weatherapp.features.historical.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmedorabi.weatherapp.features.historical.viewmodel.HistoricalViewModel


@Composable
fun HistoricalScreen(
    cityName: String = "",
    viewModel: HistoricalViewModel = hiltViewModel(),
    items: List<String> = emptyList(), // Replace String with your data model
    onItemClick: ((String) -> Unit)? = null // Handle click events on items
) {

    val citiesResponse by viewModel.citiesResponse.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getCitiesResponseFlow(cityName.lowercase())
    }

    Column {

        Spacer(Modifier.height(10.dp))
        Text(
            text = "History Data",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp)
        )

        Spacer(Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(citiesResponse.orEmpty()) { item ->
                HistoryItem(
                    dateTime = item.dateTime,
                    description = item.desc + ",",
                    temperature = item.temp.toString() + "\u2103",
                    onInfoClick = {},
                    onCardClick = {}
                )
            }
        }
    }

}

//@Composable
//fun HistoricalItem(
//    item: String,
//    onClick: () -> Unit
//) {
//    // Replace with the layout of your historical_item
//    Text(
//        text = item,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .clickable { onClick() }
//    )
//}