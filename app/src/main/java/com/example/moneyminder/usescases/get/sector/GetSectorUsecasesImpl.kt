package com.example.moneyminder.usescases.get.sector

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Sector
import retrofit2.Response
import javax.inject.Inject

class GetSectorUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : GetSectorUsecases {
    override suspend fun getSectorByNombre(nombre: String): Response<Sector> {
        return moneyMinderRepository.getSectorByName(nombre)
    }
}