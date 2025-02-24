package com.chesco.proyecto_di

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalTime

@Composable
fun HoursScreen(viewModel: ViewModelHoras = viewModel()) {
    val ciudadSeleccionada = viewModel.ciudadSeleccionada
    val horaCiudad = viewModel.horasCiudad
    Column(modifier = Modifier.fillMaxSize()
        .padding(10.dp)
        .padding(PaddingValues())
    ) {
        HoraActual(ciudad = ciudadSeleccionada, viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        RestoCiudades(horaCiudad)
    }
}

//Menú desplegable para seleccionar la ciudad.
@Composable
fun SelectCiudad(viewModel: ViewModelHoras) {
    val ciudades = listOf(
        "Madrid", "París", "Londres", "Porto Alegre", "Acapulco", "Vancouver",
        "Houston", "Casablanca", "Osaka", "Melbourne", "Berlín", "Dubai"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf(viewModel.ciudadSeleccionada) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedCity, fontSize = 45.sp)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ciudades.forEach { city ->
                DropdownMenuItem(
                    text = { Text(city) },
                    onClick = {
                        selectedCity = city
                        viewModel.actualizarCiudadSeleccionada(city)
                        expanded = false
                    }
                )
            }
        }
    }
}

//Muestra la hora, la ciudad seleccionada y su mapa.
@Composable
fun HoraActual(ciudad: String, viewModel: ViewModelHoras) {
    val cityImage = cityImages[ciudad] ?: R.drawable.schedule
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = cityImage),
            contentDescription = "$ciudad Map",
            modifier = Modifier.size(150.dp)
        )
        SelectCiudad(viewModel)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TimePickerWithDialog(viewModel)
        }
    }
}

//Muestra la hora en las demás ciudades, junto con sus mapas.
@Composable
fun RestoCiudades(horas: Map<String, String>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(horas.toList()) { (city, time) ->
            val cityImage = cityImages[city] ?: R.drawable.schedule
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = cityImage),
                    contentDescription = "$city Map",
                    modifier = Modifier.size(50.dp)
                )
                Text(city, style = MaterialTheme.typography.bodyLarge)
                Text(time, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

//Time picker para elegir la hora por el usuario.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerWithDialog(viewModel: ViewModelHoras) {
    var selectedHour by remember { mutableIntStateOf(0) }
    var selectedMinute by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(state = timeState)
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = {
                        showDialog = false
                        selectedHour = timeState.hour
                        selectedMinute = timeState.minute
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(onClick = { showDialog = true }) {
            Text(text = "${LocalTime.of(timeState.hour, timeState.minute)}", fontSize = 50.sp)
        }
        viewModel.actualizarHoraSeleccionada(LocalTime.of(timeState.hour, timeState.minute))
    }
}

