package com.example.moneyminder.usescases.get.cartera

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Cartera
import retrofit2.Response
import javax.inject.Inject

class GetCarteraUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : GetCarteraUsecases {
    override suspend fun getCartera(numCuenta: String): Response<Cartera> {
        return moneyMinderRepository.getCartera(numCuenta)
    }
}