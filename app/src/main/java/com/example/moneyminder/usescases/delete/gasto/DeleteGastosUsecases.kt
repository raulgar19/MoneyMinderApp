package com.example.moneyminder.usescases.delete.gasto

import retrofit2.Response

interface DeleteGastosUsecases {
    suspend fun deleteGastos(
        id: Int
    ): Response<Void>
}