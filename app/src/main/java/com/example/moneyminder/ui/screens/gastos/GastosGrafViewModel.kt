package com.example.moneyminder.ui.screens.gastos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.get.gasto.GetGastosUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class GastosGrafViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getGastosUsecases: GetGastosUsecases
) : ViewModel() {
    var gastos : List<Gasto>? = null
    var usuario : Usuario? = null
    var showError by mutableStateOf(false)
    var gastosTotales by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesTransporte by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesAlimentacion by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesOcio by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesAgua by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesLuz by mutableStateOf(BigDecimal.ZERO)
    var gastosTotalesHipoteca by mutableStateOf(BigDecimal.ZERO)

    fun getUsuarioByCorreo(correo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    getGastosByUsuario(usuario!!)
                } else {
                    showError = true
                }
            }
        }
    }

    fun getGastosByUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getGastosUsecases.getGastosByUsuario(usuario.id)
            if (response.isSuccessful) {
                gastos = response.body()
                getGastosTotalesByUsuario(usuario.id)
            } else {
                showError = true
            }
        }
    }

    fun getGastosTotalesByUsuario(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getGastosUsecases.getGastosTotalesByUsuario(usuarioId)
            if (response.isSuccessful) {
                gastosTotales = response.body()
                getGastosTotalesByUsuarioAndTransporte(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndTransporte(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Transporte"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesTransporte = response.body()
                getGastosTotalesByUsuarioAndAlimentacion(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndAlimentacion(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Alimentación"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesAlimentacion = response.body()
                getGastosTotalesByUsuarioAndOcio(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndOcio(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Ocio"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesOcio = response.body()
                getGastosTotalesByUsuarioAndAgua(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndAgua(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Agua"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesAgua = response.body()
                getGastosTotalesByUsuarioAndLuz(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndLuz(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Luz"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesLuz = response.body()
                getGastosTotalesByUsuarioAndHipoteca(usuarioId)
            } else {
                showError = true
            }
        }
    }

    private fun getGastosTotalesByUsuarioAndHipoteca(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val nombre = "Hipoteca"
            val response = getGastosUsecases.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
            if (response.isSuccessful) {
                gastosTotalesHipoteca = response.body()
                getGastosTotalesByUsuarioAndTransporte(usuarioId)
            } else {
                showError = true
            }
        }
    }
}