package com.chesco.proyecto_di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherScreen() {
    LazyColumn {
        item {
            val isFahrenheit = fahrenheitCheckbox()
            var temperatura = temperatureSlider()
            var tFahrenheit = 0
            if (!isFahrenheit) tFahrenheit = convert(temperatura, isFahrenheit)
        }
    }
}

fun convert(temperatura: Int, fahrenheit: Boolean): Int {
    var tConvertida = 0
    if (fahrenheit) tConvertida = (temperatura - 32) * 5/9
    else tConvertida = (temperatura * (9/5)) + 32
    return tConvertida
}

@Composable
fun fahrenheitCheckbox(): Boolean {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Text("Entrada en ºF")
    }
    return checked
}

@Composable
fun temperatureSlider(): Int {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column (
        Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)){
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it},
            valueRange = -30f..55f
        )
    }
    return sliderPosition.toInt()
}

@Composable
fun IconoTemperatura(temperatura: Int){
    if (temperatura <= 12 && temperatura >= -30) {
        Icon(
            painter = painterResource(id = R.drawable.ac_unit),
            contentDescription = "Localized description",
        )
    } else if (temperatura in 13..25) {
        Icon(
            painter = painterResource(id = R.drawable.cloud),
            contentDescription = "Localized description",
        )
    }  else if (temperatura in 26..55) {
        Icon(
            painter = painterResource(id = R.drawable.sunny),
            contentDescription = "Localized description",
        )
    }
}