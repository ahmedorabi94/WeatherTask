package com.ahmedorabi.weatherapp.features.add_city.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedorabi.weatherapp.R


@Composable
fun CityCard(
    cityName: String,
    onCardClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 3.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location_city_24px),
                    contentDescription = "City Icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Unspecified
                )
            }

            Text(
                text = cityName,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clickable { onCardClick() },
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily.Default,
                maxLines = 1
            )

            IconButton(
                onClick = onInfoClick,
                modifier = Modifier.size(35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_24px),
                    contentDescription = "Info Icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Unspecified
                )
            }
        }
    }
}