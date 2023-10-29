package com.example.moneyminder.usescases.delete.ingreso

import com.example.moneyminder.datasource.MoneyMinderRepository
import retrofit2.Response
import javax.inject.Inject

class DeleteIngresosUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : DeleteIngresosUsecases {
    override suspend fun deleteIngresos(
        id: Int
    ): Response<Void> {
        return moneyMinderRepository.deleteIngresos(id)
    }
}