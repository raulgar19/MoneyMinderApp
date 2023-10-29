package com.example.moneyminder.usescases.get.gasto

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Gasto
import retrofit2.Response
import java.math.BigDecimal
import javax.inject.Inject

class GetGastosUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : GetGastosUsecases {
    override suspend fun getGastosByUsuario(id: Int): Response<List<Gasto>> {
        return moneyMinderRepository.getGastosByUsuario(id)
    }

    override suspend fun getGastosTotalesByUsuario(usuarioId: Int): Response<BigDecimal> {
        return moneyMinderRepository.getGastosTotalesByUsuario(usuarioId)
    }

    override suspend fun getGastosTotalesByUsuarioAndCategoria(
        usuarioId: Int,
        nombre: String
    ): Response<BigDecimal> {
        return moneyMinderRepository.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
    }
}