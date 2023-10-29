package com.example.moneyminder.usescases.delete.cartera

import retrofit2.Response

interface DeleteCarteraUsecases {
    suspend fun deleteCartera(
        id: Int
    ): Response<Void>
}