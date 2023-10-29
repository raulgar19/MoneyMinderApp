package com.example.moneyminder.usescases.get.cartera

import com.example.moneyminder.ui.models.Cartera
import retrofit2.Response

interface GetCarteraUsecases {
    suspend fun getCartera(numCuenta: String): Response<Cartera>
}