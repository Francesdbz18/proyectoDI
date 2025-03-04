package com.chesco.proyecto_di

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SuerteScreen(
    uistate:MainSuerteState,
    onSendButtonClicked: () -> Unit,
) {
    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Box (Modifier.border(1.dp, Color.LightGray, shape = RoundedCornerShape(1.dp)) .background(Color.LightGray) , contentAlignment = Alignment.Center){
            Column (Modifier.fillMaxWidth().padding(24.dp)){
                Text(
                    " ¡¡ E N H O R A B U E N A !! " +
                            "\nSalud: ${uistate.salud}" +
                            "\nDinero: ${uistate.dinero}" +
                            "\nAmor: ${uistate.amor}" +
                            "\nLa media aritmética de los tres factores es de ${(uistate.amor + uistate.dinero + uistate.salud) / 3}, usted puede considerarse una persona afortunada. ¡¡No malgaste su suerte, sea prudente!!"
                )
                OutlinedButton(
                    onClick =
                    onSendButtonClicked
                ) { Text("Volver a preguntar") }
            }
        }
    }
}