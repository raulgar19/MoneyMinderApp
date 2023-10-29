package com.example.moneyminder.usescases.get.ingreso

import com.example.moneyminder.ui.models.Ingreso
import retrofit2.Response
import java.math.BigDecimal

interface GetIngresosUsecases {
    suspend fun getIngresosByUsuario(
        id: Int
    ): Response<List<Ingreso>>

    suspend fun getIngresosTotalesByUsuario(
        id: Int
    ): Response<BigDecimal>
}