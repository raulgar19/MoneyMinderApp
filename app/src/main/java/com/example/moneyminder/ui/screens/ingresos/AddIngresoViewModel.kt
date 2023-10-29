package com.example.moneyminder.ui.screens.ingresos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.add.ingreso.AddIngresoUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class AddIngresoViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val addIngresoUsecases: AddIngresoUsecases
) : ViewModel() {
    var cantidad: BigDecimal by mutableStateOf(BigDecimal.ZERO)
    var usuario: Usuario? = null
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(
        correo: String,
        cantidad: BigDecimal
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    addIngresoByUsuario(usuario!!, cantidad)
                } else {
                    showError = true
                }
            }
        }
    }

    private fun addIngresoByUsuario(
        usuario: Usuario,
        cantidad: BigDecimal
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val ingreso = Ingreso(0, cantidad, Timestamp(System.currentTimeMillis()), usuario)
            val response = addIngresoUsecases.addIngresoByUsuario(ingreso)
            if (response != null && response.isSuccessful) {
                response.body()
            }
        }
    }
}