package com.chesco.proyecto_di

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//ViewModel para la pantalla de ciudades.
class CiudadViewModel : ViewModel() {
    // Estado para la ciudad seleccionada
    var ciudadSeleccionada = mutableStateOf<Ciudad?>(null)
        private set

    // Estado para el servicio seleccionado
    var servicioSeleccionado = mutableStateOf<String?>(null)
        private set

    // Función para actualizar la ciudad seleccionada
    fun actualizarCiudadSeleccionada(ciudad: Ciudad) {
        ciudadSeleccionada.value = ciudad
    }

    // Función para actualizar el servicio seleccionado
    fun actualizarServicioSeleccionado(servicio: String) {
        servicioSeleccionado.value = servicio
    }
}