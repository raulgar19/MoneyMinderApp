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
import javax.inject.Inject

@HiltViewModel
class GastosViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getGastosUsecases: GetGastosUsecases
) : ViewModel() {
    var gastos : List<Gasto>? = null
    var usuario : Usuario? = null
    var showError by mutableStateOf(false)

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
            if (response != null) {
                if (response.isSuccessful) {
                    gastos = response.body()
                } else {
                    showError = true
                }
            }
        }
    }
}