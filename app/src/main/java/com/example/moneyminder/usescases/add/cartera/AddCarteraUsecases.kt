package com.example.moneyminder.usescases.add.cartera

import com.example.moneyminder.ui.models.Cartera
import retrofit2.Response

interface AddCarteraUsecases {
    suspend fun addCartera(nuevaCartera: Cartera): Response<Cartera>
}