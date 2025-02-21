package com.chesco.proyecto_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
fun CustomScaffold() {
    val navController = rememberNavController()
    Scaffold(
        // Barra superior
        topBar = { CustomTopBar() },

        // Barra inferior
        bottomBar = { BottomBar(navController = navController) },

        // Contenido principal
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "temperatura",
                Modifier.padding(innerPadding)
            ) {
                composable("temperatura") { TemperatureContent() }
                composable("horas") { HoursScreen() }
                composable("contactos") { ContactsScreen() }
            }
        }
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
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("temperatura", ImageVector.vectorResource(R.drawable.thermostat_24dp_5f6368_fill0_wght400_grad0_opsz24), "Temperatura"),
        BottomNavItem("horas", ImageVector.vectorResource(R.drawable.schedule), "Hora"),
        BottomNavItem("contactos", Icons.Filled.Person, "Contactos")
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    BottomNavigation (
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = contentColorFor(MaterialTheme.colorScheme.primaryContainer)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label, fontSize = 13.sp) },
                selected = currentRoute == item.route, // Marca la opción activa
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo("temperatura") { inclusive = false } // Evita duplicados en el stack
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)

@Composable
@Preview
fun AppPreview() {
    AppTheme {
        CustomScaffold()
    }
}