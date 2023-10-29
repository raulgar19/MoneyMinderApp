package com.example.moneyminder.usescases.update.usuario

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import javax.inject.Inject

class UpdateUsuarioUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : UpdateUsuarioUsecases {
    override suspend fun updateUsuario(id: Int, usuarioActualizado: Usuario?): Response<Usuario> {
        return moneyMinderRepository.updateUsuario(id, usuarioActualizado)
    }
}