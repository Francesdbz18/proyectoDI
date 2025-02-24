package com.chesco.proyecto_di

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ContactsScreen(viewModel: CiudadViewModel = viewModel()) {
    val ciudades = ciudades
    val ciudadSeleccionada = viewModel.ciudadSeleccionada.value
    val servicioSeleccionado = viewModel.servicioSeleccionado.value
    var telefonoseleccionado by remember { mutableStateOf("") }

    val contacto = ciudadSeleccionada?.contacto

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Menú desplegable para seleccionar la ciudad
        CiudadSelector(ciudades, ciudadSeleccionada) { ciudad ->
            viewModel.actualizarCiudadSeleccionada(ciudad)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menú desplegable para seleccionar el servicio
        ServicioSelector(servicioSeleccionado) { servicio ->
            viewModel.actualizarServicioSeleccionado(servicio)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mapa de la ciudad seleccionada
        Box {
            val mapaSeleccionado = cityImages[ciudadSeleccionada?.nombre] ?: R.drawable.splatnot
            Image(
                painter = painterResource(id = mapaSeleccionado),
                contentDescription = "${ciudadSeleccionada?.nombre} Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(telefonoseleccionado, modifier = Modifier
                .align(Alignment.Center), color = Color.Black, fontSize = 40.sp )
        }

        //Muestra los servicios de la ciudad que se seleccione
        ciudadSeleccionada?.let {

            when (servicioSeleccionado) {
                "Emergencias" -> {
                    telefonoseleccionado = it.emergencias
                }
                "Policía" -> {
                    telefonoseleccionado = it.policia
                }
                "Bomberos" -> {
                    telefonoseleccionado = it.bomberos
                }
                "Oficina de Información Turística" -> {
                    telefonoseleccionado = it.oficinaInformacionTurismo
                }
                "Contacto" -> {
                    // Información de contacto
                    contacto?.let {
                        Text(
                            text = "Contacto: ${it.nombre}",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Teléfono: ${it.telefono}",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "Email: ${it.email}",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        telefonoseleccionado = it.telefono
                    }
                }
            }
        }
    }
}

@Composable
fun CiudadSelector(ciudades: List<Ciudad>, ciudadSeleccionada: Ciudad?, onCitySelected: (Ciudad) -> Unit) {
    val ciudadNames = ciudades.map { it.nombre }

    var expanded by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf(ciudadSeleccionada?.nombre ?: ciudadNames.first()) }

    // DropdownButton
    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ciudad: $selectedCity") // Muestra solo la ciudad seleccionada
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ciudadNames.forEach { ciudad ->
                DropdownMenuItem(
                    text = { Text(ciudad) },
                    onClick = {
                        val ciudadSeleccionada = ciudades.first { it.nombre == ciudad }
                        onCitySelected(ciudadSeleccionada)
                        selectedCity = ciudad
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ServicioSelector(servicioSeleccionado: String?, onServicioSelected: (String) -> Unit) {
    val servicios = listOf("Emergencias", "Policía", "Bomberos", "Oficina de Información Turística", "Contacto")
    var expanded by remember { mutableStateOf(false) }
    var selectedServicio by remember { mutableStateOf(servicioSeleccionado ?: servicios.first()) }

    // DropdownButton
    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Servicio: $selectedServicio") // Muestra solo el servicio seleccionado
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            servicios.forEach { servicio ->
                DropdownMenuItem(
                    text = { Text(servicio) },
                    onClick = {
                        onServicioSelected(servicio)
                        selectedServicio = servicio
                        expanded = false
                    }
                )
            }
        }
    }
}