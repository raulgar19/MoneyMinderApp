package com.example.moneyminder.datasource

import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Sector
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import java.math.BigDecimal

interface MoneyMinderRepository {
    suspend fun getUsuario(
        correo: String, pass: String
    ): Response<Usuario>

    suspend fun addUsuario(
        nuevoUsuario: Usuario
    ): Response<Usuario>

    suspend fun addCartera(
        nuevaCartera: Cartera
    ): Response<Cartera>

    suspend fun getCartera(
        numCuenta: String
    ): Response<Cartera>

    suspend fun getUsuarioByCorreo(
        correo: String
    ): Response<Usuario>

    suspend fun getIngresosByUsuario(
        id: Int
    ): Response<List<Ingreso>>

    suspend fun getGastosByUsuario(
        id: Int
    ): Response<List<Gasto>>

    suspend fun getSectorByName(
        nombre: String
    ): Response<Sector>

    suspend fun addGasto(
        nuevoGasto: Gasto
    ): Response<Gasto>

    suspend fun addIngreso(
        ingreso: Ingreso
    ): Response<Ingreso>

    suspend fun getGastosTotalesByUsuario(
        usuarioId: Int
    ): Response<BigDecimal>

    suspend fun getGastosTotalesByUsuarioAndCategoria(
        usuarioId: Int, nombre: String
    ): Response<BigDecimal>

    suspend fun getIngresosTotalesByUsuario(
        id: Int
    ): Response<BigDecimal>

    suspend fun updateUsuario(
        id: Int,
        usuarioActualizado: Usuario?
    ): Response<Usuario>

    suspend fun deleteUsuario(
        id: Int
    ): Response<Void>

    suspend fun deleteIngresos(
        id: Int
    ): Response<Void>

    suspend fun deleteGastos(
        id: Int
    ): Response<Void>

    suspend fun deleteCartera(
        id: Int
    ): Response<Void>
}