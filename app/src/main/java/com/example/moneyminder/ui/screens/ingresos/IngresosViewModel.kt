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
import javax.inject.Inject

@HiltViewModel
class IngresosViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getIngresosUsecases: GetIngresosUsecases
) : ViewModel() {
    var ingresos : List<Ingreso>? = null
    var usuario : Usuario? = null
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(correo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    getIngresosByUsuario(usuario!!)
                } else {
                    showError = true
                }
            }
        }
    }

    fun getIngresosByUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getIngresosUsecases.getIngresosByUsuario(usuario.id)
            if (response != null) {
                if (response.isSuccessful) {
                    ingresos = response.body()
                } else {
                    showError = true
                }
            }
        }
    }
}