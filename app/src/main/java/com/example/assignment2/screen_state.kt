package com.example.assignment2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed interface ScreenState<out T> {
    object Initial : ScreenState<Nothing>
    object Loading : ScreenState<Nothing>
    data class Success<T>(val data: T) : ScreenState<T>
    data class Error(val message: String) : ScreenState<Nothing>
}

class MainViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState<List<String>>>(ScreenState.Initial)
    val screenState: StateFlow<ScreenState<List<String>>> = _screenState

    fun fetchData() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch {
            try {
                delay(2000)
                //Error
//                throw Exception("Ошибка при загрузке данных")
                //Susses
                val data = listOf("Фильм 1", "Фильм 2", "Фильм 3")
                _screenState.value = ScreenState.Success(data)
            } catch (e: Exception) {
                _screenState.value = ScreenState.Error("Ошибка при загрузке данных")
            }
        }
    }
}

@Composable
fun ScreenContent(viewModel: MainViewModel = viewModel()) {
    val screenState by viewModel.screenState.collectAsState()

    when (screenState) {
        is ScreenState.Initial -> {
            InitialScreen {
                viewModel.fetchData()
            }
        }
        is ScreenState.Loading -> {
            LoadingScreen()
        }
        is ScreenState.Success -> {
            SuccessScreen(data = (screenState as ScreenState.Success<List<String>>).data)
        }
        is ScreenState.Error -> {
            ErrorScreen(message = (screenState as ScreenState.Error).message) {
                viewModel.fetchData()
            }
        }
    }
}

@Composable
fun InitialScreen(onStartClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onStartClick) {
            Text("Начать загрузку")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun SuccessScreen(data: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        data.forEach { item ->
            Text(text = item, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetryClick) {
            Text("Повторить")
        }
    }
}


