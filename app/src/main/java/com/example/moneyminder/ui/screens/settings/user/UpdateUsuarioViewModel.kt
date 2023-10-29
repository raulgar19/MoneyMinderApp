package com.example.moneyminder.ui.screens.settings.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import com.example.moneyminder.usescases.update.usuario.UpdateUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateUsuarioViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val updateUsuarioUsecases: UpdateUsuarioUsecases
): ViewModel() {
    var usuario : Usuario? = null
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(correo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                } else {
                    showError = true
                }
            }
        }
    }

    fun updateUsuario(nombre: String, apellido: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val usuarioActualizado = usuario?.copy(nombre = nombre, apellido = apellido)
            val response = updateUsuarioUsecases.updateUsuario(usuarioActualizado!!.id, usuarioActualizado)
            if (response.isSuccessful) {
                usuario = usuarioActualizado
            } else {
                showError = true
            }
        }
    }
}