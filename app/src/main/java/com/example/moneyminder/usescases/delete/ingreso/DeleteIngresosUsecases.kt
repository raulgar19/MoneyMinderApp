package com.example.moneyminder.usescases.delete.ingreso

import retrofit2.Response

interface DeleteIngresosUsecases {
    suspend fun deleteIngresos(
        id: Int
    ): Response<Void>
}