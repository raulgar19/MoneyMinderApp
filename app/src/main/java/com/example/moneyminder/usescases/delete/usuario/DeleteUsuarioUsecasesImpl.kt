package com.example.moneyminder.usescases.delete.usuario

import com.example.moneyminder.datasource.MoneyMinderRepository
import retrofit2.Response
import javax.inject.Inject

class DeleteUsuarioUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
): DeleteUsuarioUsecases {
    override suspend fun deleteUsuario(id: Int): Response<Void> {
        return moneyMinderRepository.deleteUsuario(id)
    }
}