package com.chesco.proyecto_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chesco.proyecto_di.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                CustomScaffold()
            }
        }
    }
}

@Composable
fun CustomScaffold() {
    Scaffold(
        // Barra superior
        topBar = { CustomTopBar() },

        // Barra inferior
        bottomBar = { CustomBottomBar() },

        // Contenido principal
        content = { padding ->
            CustomContent(padding)
        }
    )
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),

        // Contenido de la aplicación
        content = {
            WeatherScreen()
        }
    )
}

@Composable
fun CustomBottomBar() {
    BottomAppBar(
        actions = {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                TextButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.thermostat_24dp_5f6368_fill0_wght400_grad0_opsz24),
                        contentDescription = "Temperatura"
                    )
                    Text(" Temperatura")
                }
                TextButton(onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.schedule),
                        contentDescription = "Localized description",
                    )
                    Text(" Hora")
                }
                TextButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                    )
                    Text(" Contactos")
                }
            }
        },
        contentColor = colorScheme.primary,
        containerColor = colorScheme.primaryContainer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.surfaceContainer,
            titleContentColor = colorScheme.surface,
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.splatnot),
                contentDescription = "Flecha",
                modifier = Modifier
                    .size(100.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
@Preview
fun AppPreview() {
    AppTheme {
        CustomScaffold()
    }
}