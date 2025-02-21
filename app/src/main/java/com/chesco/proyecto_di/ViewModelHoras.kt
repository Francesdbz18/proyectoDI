package com.chesco.proyecto_di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


//ViewModel para la pantalla de Horas.
class ViewModelHoras : ViewModel() {
    var ciudadSeleccionada by mutableStateOf("Madrid")
        private set

    var horaSeleccionada by mutableStateOf(LocalTime.of(12, 0)) // Hora por defecto
        private set

    var horasCiudad by mutableStateOf(emptyMap<String, String>())
        private set

    private val cityTimeZones = mapOf(
        "Madrid" to "Europe/Madrid",
        "París" to "Europe/Paris",
        "Londres" to "Europe/London",
        "Porto Alegre" to "America/Sao_Paulo",
        "Acapulco" to "America/Mexico_City",
        "Vancouver" to "America/Vancouver",
        "Houston" to "America/Chicago",
        "Casablanca" to "Africa/Casablanca",
        "Osaka" to "Asia/Tokyo",
        "Melbourne" to "Australia/Melbourne",
        "Ankara" to "Europe/Istanbul",
        "Dubai" to "Asia/Dubai"
    )

    init {
        actualizarHoras()
    }

    fun actualizarCiudadSeleccionada(ciudad: String) {
        ciudadSeleccionada = ciudad
        actualizarHoras()
    }

    fun actualizarHoraSeleccionada(hora: LocalTime) {
        horaSeleccionada = hora
        actualizarHoras()
    }

    private fun actualizarHoras() {
        val zonaReferencia = ZoneId.of(cityTimeZones[ciudadSeleccionada] ?: "UTC")
        val referenciaHora = horaSeleccionada.atDate(LocalDate.now()).atZone(zonaReferencia)

        horasCiudad = cityTimeZones.mapValues { (_, zoneId) ->
            val zona = ZoneId.of(zoneId)
            val horaConvertida = referenciaHora.withZoneSameInstant(zona).toLocalTime()
            horaConvertida.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }
}