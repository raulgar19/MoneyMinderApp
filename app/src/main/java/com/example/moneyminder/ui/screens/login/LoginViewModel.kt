package com.example.moneyminder.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases
) : ViewModel() {
    var usuario : Usuario? = null
    var correo : String by mutableStateOf("")
    var pass : String by mutableStateOf("")
    var showError by mutableStateOf(false)
    var usuarioEncontrado by mutableStateOf(false)

    fun getUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuario(correo, pass)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    usuarioEncontrado = true
                }
                else {
                    showError = true
                }
            }
        }
    }
}