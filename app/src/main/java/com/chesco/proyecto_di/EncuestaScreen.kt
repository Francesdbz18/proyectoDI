package com.chesco.proyecto_di

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EncuestaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var mostrarRespuestas by rememberSaveable { mutableStateOf(false) }
        var respuesta1 by rememberSaveable { mutableStateOf("") }
        var respuesta2 by rememberSaveable { mutableStateOf("") }
        var respuesta3 by rememberSaveable { mutableStateOf("0") }

        LazyColumn {
            item {
                Text(
                    "Encuesta de RRHH",
                    style = MaterialTheme.typography.headlineMedium
                )
                respuesta1 = preguntaRow(
                    "¿Dispone de residencia en la ciudad donde está destinado?",
                    opciones = (listOf("Alquiler", "En propiedad", "De familiares", "De amigos")),
                    true
                )
                respuesta2 = preguntaRow(
                    "¿Tiene coche propio?",
                    opciones = (listOf("Compacto", "Berlina", "SUV", "Familiar", "Deportivo")),
                    true
                )
                respuesta3 = preguntaRow(
                    "Número de hijos",
                    opciones = (listOf("0", "1", "2", "3", "4", "5")),
                    false
                )
            }
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    var boton by rememberSaveable { mutableStateOf("Enviar") }
                    TextButton(onClick = {
                        boton = "Enviado"
                        mostrarRespuestas = true
                    }, modifier = Modifier .border(1.dp, Color.Black, RectangleShape)) {
                        Text(boton)
                    }
                }
                Spacer(Modifier.size(20.dp))
            }
            if (mostrarRespuestas) {
                item { Respuestas(respuesta1, R.drawable.house, "Casa")}
                item{ Respuestas(respuesta2, R.drawable.car, "Coche")}
                item {
                    var numero = 0
                    if (respuesta3.isNotBlank()) numero = respuesta3.toInt()
                    RespuestaSlider(numero)
                }
            }
        }
    }
}



@Composable
fun preguntaRow(pregunta: String, opciones: List<String>, hasCheckbox: Boolean): String {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var respuesta by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }
    if (!hasCheckbox) checked = true
    Row (
        Modifier
            .border(1.dp, Color.LightGray, RectangleShape)
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(pregunta, Modifier.width(150.dp), fontSize = 10.sp)
        if (hasCheckbox) Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = !checked
                respuesta = opciones[0]
            }
        )
        Box(Modifier.border(1.dp, Color.Black, RectangleShape) .padding(2.dp) .height(35.dp) .width(120.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = { expanded = !expanded }, modifier = Modifier.fillMaxWidth()) {
                    Text(respuesta)
                }
                if (checked) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        opciones.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    respuesta = opcion
                                    expanded = false
                                }
                            )

                        }
                    }
                }
            }
        }
    }
    if (!checked) respuesta = ""
    return respuesta
}

@Composable
fun Respuestas(respuesta1: String, imagen: Int, texto: String) {
    Row (Modifier.fillMaxWidth(), verticalAlignment =  Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
        Text(texto)
        Box(contentAlignment = Alignment.Center) {
            Image(
                painterResource(imagen),
                contentDescription = texto,
                Modifier.size(100.dp)

            )
            if (respuesta1.isBlank())
                Image(
                    painterResource(R.drawable.unchecked),
                    contentDescription = "Unchecked",
                    Modifier.size(75.dp)
                )
            else
                Image(
                    painterResource(R.drawable.checked),
                    contentDescription = "Checked",
                    Modifier.size(75.dp)

                )
        }
        Box(Modifier.padding(2.dp) .height(35.dp) .width(120.dp)) {
            Text(respuesta1)
        }
    }
}

@Composable
fun RespuestaSlider(respuesta3: Int) {
    Row (Modifier.fillMaxWidth(), verticalAlignment =  Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Niños")
        Image(
            painterResource(R.drawable.baby),
            contentDescription = "Bebé",
            Modifier.size(100.dp)
        )
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Slider(
                value = respuesta3.toFloat(),
                onValueChange = {},
                enabled = false,
                modifier = Modifier.width(130.dp),
                valueRange = 0f..5f,
                steps = 4
            )
            Text(text = respuesta3.toString())
        }
    }
}