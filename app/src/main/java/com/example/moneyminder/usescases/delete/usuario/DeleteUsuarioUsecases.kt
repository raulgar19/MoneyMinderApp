package com.example.moneyminder.usescases.delete.usuario

import retrofit2.Response

interface DeleteUsuarioUsecases {
    suspend fun deleteUsuario(id: Int): Response<Void>
}