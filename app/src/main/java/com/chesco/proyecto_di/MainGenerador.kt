package com.chesco.proyecto_di

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun GeneradorScreen(
    uistate:MainSuerteState,
    navController: NavController,
    onPreguntarClicked: () -> Unit,
    onReiniciarClicked: () -> Unit,
    ) {
    val amor = uistate.amor
    val dinero = uistate.dinero
    val salud = uistate.salud
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background (Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
            Column {
                Text("Salud:", fontSize = 30.sp)
                Text("$salud", fontSize = 30.sp)
            }
            Column {
                Text("Dinero:", fontSize = 30.sp)
                Text("$dinero", fontSize = 30.sp)
            }
            Column {
                Text("Amor:", fontSize = 30.sp)
                Text("$amor", fontSize = 30.sp)
            }
        }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
            Button(onClick = onPreguntarClicked) { Text("Preguntar", color = Color.White) }
            Button (onClick = onReiniciarClicked) { Text("Reiniciar", color = Color.White) }
        }
        if (((amor+dinero+salud)/3) > 69)
            navController.navigate("suerte")
    }
}

