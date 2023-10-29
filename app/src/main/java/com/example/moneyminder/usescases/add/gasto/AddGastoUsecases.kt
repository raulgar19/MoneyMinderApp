package com.example.moneyminder.usescases.add.gasto

import com.example.moneyminder.ui.models.Gasto
import retrofit2.Response

interface AddGastoUsecases {
    suspend fun addGastoByUsuario(nuevoGasto: Gasto): Response<Gasto>

}
