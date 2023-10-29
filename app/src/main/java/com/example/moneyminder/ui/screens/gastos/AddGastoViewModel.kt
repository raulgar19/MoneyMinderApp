package com.example.moneyminder.ui.screens.gastos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Sector
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.usescases.add.gasto.AddGastoUsecases
import com.example.moneyminder.usescases.get.sector.GetSectorUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class AddGastoViewModel @Inject constructor(
    private val getUsuarioUsecases: GetUsuarioUsecases,
    private val getSectorUsecases: GetSectorUsecases,
    private val addGastoUsecases: AddGastoUsecases
) : ViewModel() {
    var cantidad: BigDecimal by mutableStateOf(BigDecimal.ZERO)
    var sector: Sector? = null
    var usuario: Usuario? = null
    var showError by mutableStateOf(false)

    fun getUsuarioByCorreo(
        correo: String,
        categoria: String,
        cantidad: BigDecimal
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsuarioUsecases.getUsuarioByCorreo(correo)
            if (response != null) {
                if (response.isSuccessful) {
                    usuario = response.body()
                    getSectorByNombre(usuario!!, categoria, cantidad)
                } else {
                    showError = true
                }
            }
        }
    }

    private fun getSectorByNombre(
        usuario: Usuario,
        categoria: String,
        cantidad: BigDecimal
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val sectorResponse = getSectorUsecases.getSectorByNombre(categoria)
            if (sectorResponse != null) {
                if (sectorResponse.isSuccessful) {
                    sector = sectorResponse.body()
                    addGastoByUsuario(usuario, sector!!, cantidad)
                } else {
                    showError = true
                }
            }
        }
    }

    private fun addGastoByUsuario(
        usuario: Usuario,
        sector: Sector,
        cantidad: BigDecimal
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val nuevoGasto = Gasto(0, cantidad, Timestamp(System.currentTimeMillis()), sector, usuario)
            val response = addGastoUsecases.addGastoByUsuario(nuevoGasto)
            if (response != null && response.isSuccessful) {
                response.body()
            }
        }
    }
}