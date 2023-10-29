package com.example.moneyminder.usescases.add.usuario

import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response

interface AddUsuarioUsecases {
    suspend fun addUsuario(nuevoUsuario: Usuario): Response<Usuario>
}