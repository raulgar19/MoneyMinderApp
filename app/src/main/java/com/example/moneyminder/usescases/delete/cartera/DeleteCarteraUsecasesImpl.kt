package com.example.moneyminder.usescases.delete.cartera

import com.example.moneyminder.datasource.MoneyMinderRepository
import retrofit2.Response
import javax.inject.Inject

class DeleteCarteraUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : DeleteCarteraUsecases {
    override suspend fun deleteCartera(id: Int): Response<Void> {
        return moneyMinderRepository.deleteCartera(id)
    }
}