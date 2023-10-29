package com.example.moneyminder.usescases.add.gasto

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Gasto
import retrofit2.Response
import javax.inject.Inject

class AddGastoUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : AddGastoUsecases {
    override suspend fun addGastoByUsuario(gasto: Gasto): Response<Gasto> {
        return moneyMinderRepository.addGasto(gasto)
    }
}