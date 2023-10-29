package com.example.moneyminder.usescases.delete.gasto

import com.example.moneyminder.datasource.MoneyMinderRepository
import retrofit2.Response
import javax.inject.Inject

class DeleteGastosUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : DeleteGastosUsecases {
    override suspend fun deleteGastos(
        id: Int
    ): Response<Void> {
        return moneyMinderRepository.deleteGastos(id)
    }
}