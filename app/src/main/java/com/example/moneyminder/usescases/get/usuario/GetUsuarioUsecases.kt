package com.example.moneyminder.usescases.get.usuario

import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response

interface GetUsuarioUsecases {
    suspend fun getUsuario(
        correo: String, pass: String
    ): Response<Usuario>?

    suspend fun getUsuarioByCorreo(
        correo: String
    ): Response<Usuario>?
}