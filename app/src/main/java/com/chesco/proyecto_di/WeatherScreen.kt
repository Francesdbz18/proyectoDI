package com.chesco.proyecto_di

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun TemperatureContent(
    viewModel: TemperatureViewModel = viewModel(),
) {
    val temperatura = viewModel.temperatureCelsius
    val isFahrenheit = viewModel.isFahrenheit
    val temperaturasGuardadas = viewModel.listTemperatures
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Conversión de temperatura",
            style = MaterialTheme.typography.headlineSmall
        )//revisar la tipografia
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "Seleccione la temperatura: ",
            fontSize = 18.sp
        )
        Slider(
            value = temperatura,
            onValueChange = { viewModel.updateTemperature(it) },
            modifier = Modifier.width(280.dp),
            valueRange = -30f..55f
        )
        Row(
            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isFahrenheit,
                onCheckedChange = { viewModel.toggleFahrenheit() }
            )
            Text("Entrada en ºF")
        }
        Text("${if (isFahrenheit) convertir(temperatura.toInt()) else temperatura.toInt()} ${if (isFahrenheit) "°F" else "°C"} ")
        Button(onClick = { viewModel.saveTemperature() }) {
            Text("Guardar", fontSize = 18.sp)
        }
        Box(
            modifier = Modifier
                .width(180.dp)
                .height(170.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bigthermostat),
                contentDescription = "Imagen Termometro",
                modifier = Modifier.fillMaxSize()
            )
            Row{
                Text(
                    "${temperatura.toInt()}°C ",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "${convertir(temperatura.toInt())}°F",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 20.sp
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(temperaturasGuardadas) { temp ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val iconRes = when (temp) {
                        in -30..12 -> R.drawable.ac_unit
                        in 13..25 -> R.drawable.cloud
                        in 26..55 -> R.drawable.sunny
                        else -> {R.drawable.ac_unit}
                    }
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Temperature Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$temp°C / ${convertir(temp)}°F", fontSize = 20.sp)
                }
            }
        }
    }
}
fun convertir(celsius: Int): Int {
    return ((celsius * 9 / 5) + 32)
}