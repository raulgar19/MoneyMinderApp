package com.example.moneyminder.usescases.update.usuario

import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response

interface UpdateUsuarioUsecases {
    suspend fun updateUsuario(id: Int, usuarioActualizado: Usuario?): Response<Usuario>
}