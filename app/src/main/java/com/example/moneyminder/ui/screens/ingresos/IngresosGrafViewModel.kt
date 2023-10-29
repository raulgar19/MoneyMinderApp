package com.example.moneyminder.ui.screens.ingresos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.get.ingreso.GetIngresosUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class IngresosGrafViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getIngresosUsecases: GetIngresosUsecases
) : ViewModel() {
    var ingresos : List<Ingreso>? = null
    var usuario : Usuario? = null
    var ingresosTotales by mutableStateOf(BigDecimal.ZERO)
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(correo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    getIngresosByUsuario(usuario!!.id)
                } else {
                    showError = true
                }
            }
        }
    }

    fun getIngresosByUsuario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getIngresosUsecases.getIngresosByUsuario(id)
            if (response.isSuccessful) {
                ingresos = response.body()
                getIngresosTotalesByUsuario(usuario!!.id)
            } else {
                showError = true
            }
        }
    }

    fun getIngresosTotalesByUsuario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getIngresosUsecases.getIngresosTotalesByUsuario(id)
            if (response.isSuccessful) {
                ingresosTotales = response.body()
            } else {
                showError = true
            }
        }
    }
}