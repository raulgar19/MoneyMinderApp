package com.example.moneyminder.usescases.get.gasto

import com.example.moneyminder.ui.models.Gasto
import retrofit2.Response
import java.math.BigDecimal

interface GetGastosUsecases {
    suspend fun getGastosByUsuario(id: Int): Response<List<Gasto>>
    suspend fun getGastosTotalesByUsuario(usuarioId: Int): Response<BigDecimal>
    suspend fun getGastosTotalesByUsuarioAndCategoria(usuarioId: Int, nombre: String): Response<BigDecimal>
}