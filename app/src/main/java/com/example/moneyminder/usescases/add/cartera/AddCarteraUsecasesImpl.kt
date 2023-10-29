package com.example.moneyminder.usescases.add.cartera

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Cartera
import retrofit2.Response
import javax.inject.Inject

class AddCarteraUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : AddCarteraUsecases {
    override suspend fun addCartera(
        nuevaCartera: Cartera
    ): Response<Cartera> {
        return moneyMinderRepository.addCartera(nuevaCartera)
    }
}