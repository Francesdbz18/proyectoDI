package com.chesco.proyecto_di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun CounterApp() {
    var count1 by remember { mutableStateOf(0) }
    var count2 by remember { mutableStateOf(1) }
    var count3 by remember { mutableStateOf(0) }

    Column {
        Row {
            Counter(count1)
            CustomButton(onClick = { count1++ }, "HOLA", Color.Red)
        }
        Row {
            Counter(count2)
            CustomButton(onClick = { count2 = count2*2 }, "ADIÓS", Color.Red)
        }
        Row {
            Counter(count3)
            CustomButton(onClick = { count3 = count1+count2 }, "BYE", Color.Red)
        }
    }
}

@Composable
fun Counter(count: Int) {
    Text("Count: $count")
}

@Composable
fun CustomButton(onClick: () -> Unit, text: String, color: Color) {
    Button(onClick = onClick, colors = ButtonColors(containerColor = color, contentColor = ButtonDefaults.buttonColors().contentColor, disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor, disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor)) {
        Text(text)
    }
}