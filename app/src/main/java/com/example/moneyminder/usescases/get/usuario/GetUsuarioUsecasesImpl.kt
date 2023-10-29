package com.example.moneyminder.usescases.get.usuario

import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import javax.inject.Inject

class GetUsuarioUsecasesImpl @Inject constructor(
    private val moneyMinderRepository : MoneyMinderRepository
) : GetUsuarioUsecases {
    override suspend fun getUsuario(correo: String, pass: String): Response<Usuario> {
        return moneyMinderRepository.getUsuario(correo, pass)
    }

    override suspend fun getUsuarioByCorreo(correo: String): Response<Usuario> {
        return moneyMinderRepository.getUsuarioByCorreo(correo)
    }
}