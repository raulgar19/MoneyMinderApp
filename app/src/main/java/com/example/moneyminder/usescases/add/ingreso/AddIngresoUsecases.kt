package com.example.moneyminder.usescases.add.ingreso

import com.example.moneyminder.ui.models.Ingreso
import retrofit2.Response

interface AddIngresoUsecases {
    suspend fun addIngresoByUsuario(ingreso: Ingreso): Response<Ingreso>
}