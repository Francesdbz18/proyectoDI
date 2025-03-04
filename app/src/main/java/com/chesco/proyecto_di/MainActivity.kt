package com.chesco.proyecto_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun CustomScaffold(
    viewModel: MainGeneradorViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()
    Scaffold(
        // Barra superior
        topBar = { CustomTopBar() }

    )

    // Contenido principal
    { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = "generador",
            Modifier.padding(innerPadding)
        ) {
            composable("generador") {
                GeneradorScreen(uiState, navController, {
                    viewModel.update()
                }, {
                    viewModel.restart()
                })
            }
            composable("suerte") {
                SuerteScreen(uiState) {
                    navController.navigate("generador")
                    viewModel.restart()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text("Oráculo 2ºDAM")
        }
    )
}
