package com.chesco.proyecto_di

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chesco.proyecto_di.TemperatureViewModel


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureContent(
    viewModel: TemperatureViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Conversión de temperatura", style = MaterialTheme.typography.headlineSmall)//revisar la tipografia
        Spacer(modifier = Modifier.height(18.dp))
        Text("Select Temperature: ${if (Fahrenheit) celsiusAFahrenheit(temperatura.toInt()) else temperatura.toInt()} ${if (Fahrenheit) "°F" else "°C"}", fontSize = 20.sp)
        Slider(
            value = temperatura,
            onValueChange = { viewModel.updateTemperature(it) },
            modifier = Modifier .width(280.dp),
            valueRange = -30f..55f
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Celsius", fontSize = 20.sp)
            Switch(
                checked = Fahrenheit,
                onCheckedChange = { viewModel.toggleFahrenheit() }
            )
            Text("Fahrenheit", fontSize = 20.sp)
        }
        Button(onClick = { viewModel.guardarTemperaturas }) {
            Text("Guardar Temperatura", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(19.dp))

        Box(
            modifier = Modifier
                .width(170.dp)
                .height(170.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.thermometer),
                contentDescription = "Imagen Termometro",
                modifier = Modifier.fillMaxSize()
            )
            Row(){
                Text("${temperatura.toInt()}°C ",modifier = Modifier .padding(12.dp), fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("${celsiusAFahrenheit(temperatura.toInt())}°F",modifier = Modifier .padding(12.dp), fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(temperaturasGuardadas) { temp ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val iconRes = when (temp) {
                        in -30..12 -> R.drawable.snowflakes
                        in 13..25 -> R.drawable.thermometerwarm
                        in 26..55 -> R.drawable.sun
                        else -> R.drawable.thermometer
                    }
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Temperature Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$temp°C / ${celsiusAFahrenheit(temp)}°F", fontSize = 20.sp)
                }
            }
        }
    }
}
}