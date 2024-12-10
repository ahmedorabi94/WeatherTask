package com.ahmedorabi.weatherapp.features.add_city.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.features.add_city.viewmodel.AddCityViewModel

@Preview
@Composable
fun AddCityDialog(
    showDialog: MutableState<Boolean>? = null,
    onAddCity: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier,
    viewModel: AddCityViewModel = hiltViewModel()
) {

    val cityNameMutable = remember { mutableStateOf("") }


    Dialog(onDismissRequest = { showDialog?.value = false }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                  //  .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Text Input
                OutlinedTextField(
                    value = cityNameMutable.value,
                    onValueChange = { cityNameMutable.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    placeholder = { Text(text = "Add City") },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Add Button
                Button(
                    onClick = {
                        //  onAddCity?.let { it(cityName) }
                        viewModel.addCity(cityNameMutable.value)
                        showDialog?.value = false
                    },
                    modifier = Modifier
                        .width(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(
                        text = "Add",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}