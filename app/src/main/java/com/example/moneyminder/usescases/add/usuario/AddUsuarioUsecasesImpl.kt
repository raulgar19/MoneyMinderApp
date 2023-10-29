package com.example.moneyminder.usescases.add.usuario

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import javax.inject.Inject

class AddUsuarioUsecasesImpl @Inject constructor(
    private val moneyMinderRepository: MoneyMinderRepository
) : AddUsuarioUsecases {
    override suspend fun addUsuario(
        nuevoUsuario: Usuario
    ): Response<Usuario> {
        return moneyMinderRepository.addUsuario(nuevoUsuario)
    }
}