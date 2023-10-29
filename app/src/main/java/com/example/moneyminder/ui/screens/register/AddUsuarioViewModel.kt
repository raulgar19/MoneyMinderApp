package com.example.moneyminder.ui.screens.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.add.cartera.AddCarteraUsecases
import com.example.moneyminder.usescases.add.usuario.AddUsuarioUsecases
import com.example.moneyminder.usescases.get.cartera.GetCarteraUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddUsuarioViewModel @Inject constructor(
    private val addUsuarioUsecases: AddUsuarioUsecases,
    private val addCarteraUsecases: AddCarteraUsecases,
    private val getCarteraUsecases: GetCarteraUsecases
) : ViewModel() {
    var nombre: String by mutableStateOf("")
    var apellido: String by mutableStateOf("")
    var correo: String by mutableStateOf("")
    var pass: String by mutableStateOf("")
    var numCuenta: String by mutableStateOf("")
    var dinero: BigDecimal by mutableStateOf(BigDecimal.ZERO)
    var cartera: Cartera? by mutableStateOf(null)
    var checkBox : Boolean by mutableStateOf(false)
    var showError by mutableStateOf(false)
    var isEnabled : Boolean by mutableStateOf(false)

    fun addCartera() {
        viewModelScope.launch(Dispatchers.IO) {
            val nuevaCartera = Cartera(0, numCuenta, dinero)
            val response = addCarteraUsecases.addCartera(nuevaCartera)
            if (response != null && response.isSuccessful) {
                getCarteraByNumCuenta()
                addUsuario()
            }
        }
    }

    private suspend fun getCarteraByNumCuenta() {
        val response = getCarteraUsecases.getCartera(numCuenta)
        if (response != null && response.isSuccessful) {
            cartera = response.body()
        } else {
            showError = true
        }
    }

    private suspend fun addUsuario() {
        cartera?.let {
            val nuevoUsuario = Usuario(0, nombre, apellido, correo, pass, it)
            val response = addUsuarioUsecases.addUsuario(nuevoUsuario)
            if (response != null && response.isSuccessful) {
                response.body()
            }
        }
    }

    fun onCheckChange(it: Boolean) {
        checkBox = it
        isEnabled = isCheckValid(it)
    }

    private fun isCheckValid(it: Boolean): Boolean =
        it
}