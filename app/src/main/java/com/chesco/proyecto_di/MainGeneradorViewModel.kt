package com.chesco.proyecto_di

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MainGeneradorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainSuerteState())
    val uiState: StateFlow<MainSuerteState> = _uiState.asStateFlow()

    fun update() {
        _uiState.update { currentState ->
            currentState.copy(
                salud = Random.nextInt(100),
                dinero = Random.nextInt(100),
                amor = Random.nextInt(100)
            )
        }
    }

    fun restart() {
        _uiState.update { currentState ->
            currentState.copy(
                salud = 0,
                dinero = 0,
                amor = 0
            )
        }
    }
}