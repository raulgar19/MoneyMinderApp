package com.example.moneyminder.usescases.get.sector

import com.example.moneyminder.ui.models.Sector
import retrofit2.Response

interface GetSectorUsecases {
    suspend fun getSectorByNombre(nombre: String): Response<Sector>
}