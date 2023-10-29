package com.example.moneyminder.ui.screens.settings.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.delete.cartera.DeleteCarteraUsecases
import com.example.moneyminder.usescases.delete.gasto.DeleteGastosUsecases
import com.example.moneyminder.usescases.delete.ingreso.DeleteIngresosUsecases
import com.example.moneyminder.usescases.delete.usuario.DeleteUsuarioUsecases
import com.example.moneyminder.usescases.get.cartera.GetCarteraUsecases
import com.example.moneyminder.usescases.get.gasto.GetGastosUsecases
import com.example.moneyminder.usescases.get.ingreso.GetIngresosUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AjusteUsuarioViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getGastosUsecases: GetGastosUsecases,
    private val getIngresosUsecases: GetIngresosUsecases,
    private val getCarteraUsecases: GetCarteraUsecases,
    private val deleteGastosUsecases: DeleteGastosUsecases,
    private val deleteIngresosUsecases: DeleteIngresosUsecases,
    private val deleteUsuarioUsecases: DeleteUsuarioUsecases,
    private val deleteCarteraUsecases: DeleteCarteraUsecases
): ViewModel() {
    var usuario : Usuario? = null
    var cartera : Cartera? = null
    var ingresos : List<Ingreso>? = null
    var gastos : List<Gasto>? = null
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(correo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    getCarteraByUsuario()
                } else {
                    showError = true
                }
            }
        }
    }

    private fun getCarteraByUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCarteraUsecases.getCartera(usuario!!.cartera.numCuenta)
            if (response.isSuccessful) {
                cartera = response.body()
                getIngresosByUsuario()
            } else {
                showError = true
            }
        }
    }

    fun getIngresosByUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getIngresosUsecases.getIngresosByUsuario(usuario!!.id)
            if (response.isSuccessful) {
                ingresos = response.body()
                getGastosByUsuario()
            } else {
                showError = true
            }
        }
    }

    fun getGastosByUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getGastosUsecases.getGastosByUsuario(usuario!!.id)
            if (response.isSuccessful) {
                gastos = response.body()
            } else {
                showError = true
            }
        }
    }

    fun deleteIngresos() {
        viewModelScope.launch(Dispatchers.IO) {
            ingresos?.forEach { ingreso ->
                val response = deleteIngresosUsecases.deleteIngresos(ingreso.id)
                if (!response.isSuccessful) {
                    showError = true
                    return@launch
                }
            }
            deleteGastos()
        }
    }

    fun deleteGastos() {
        viewModelScope.launch(Dispatchers.IO) {
            gastos?.forEach { gasto ->
                val response = deleteGastosUsecases.deleteGastos(gasto.id)
                if (!response.isSuccessful) {
                    showError = true
                    return@launch
                }
            }
            deleteUsuario()
        }
    }

    fun deleteUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = deleteUsuarioUsecases.deleteUsuario(usuario!!.id)
            if (response.isSuccessful) {
                deleteCartera()
            } else {
                showError = true
            }
        }
    }

    fun deleteCartera() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = deleteCarteraUsecases.deleteCartera(cartera!!.id)
            if (!response.isSuccessful) {
                showError = true
            }
        }
    }
}