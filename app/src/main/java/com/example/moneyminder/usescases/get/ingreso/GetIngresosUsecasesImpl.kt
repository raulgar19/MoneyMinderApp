package com.example.moneyminder.usescases.get.ingreso

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Ingreso
import retrofit2.Response
import java.math.BigDecimal
import javax.inject.Inject

class GetIngresosUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : GetIngresosUsecases {
    override suspend fun getIngresosByUsuario(id: Int): Response<List<Ingreso>> {
        return moneyMinderRepository.getIngresosByUsuario(id)
    }

    override suspend fun getIngresosTotalesByUsuario(id: Int): Response<BigDecimal> {
        return moneyMinderRepository.getIngresosTotalesByUsuario(id)
    }
}