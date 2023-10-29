package com.example.moneyminder.usescases.add.ingreso

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Ingreso
import retrofit2.Response
import javax.inject.Inject

class AddIngresoUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : AddIngresoUsecases {
    override suspend fun addIngresoByUsuario(ingreso: Ingreso): Response<Ingreso> {
        return moneyMinderRepository.addIngreso(ingreso)
    }
}