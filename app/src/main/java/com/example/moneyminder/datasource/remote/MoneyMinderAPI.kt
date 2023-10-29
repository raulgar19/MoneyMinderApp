package com.example.moneyminder.datasource.remote

import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Sector
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface MoneyMinderAPI {
    @POST("usuarios")
    suspend fun addUsuario(
        @Body nuevoUsuario: Usuario
    ): Response<Usuario>

    @POST("carteras")
    suspend fun addCartera(
        @Body nuevaCartera: Cartera
    ): Response<Cartera>

    @GET("usuarios/correoAndPass")
    suspend fun getUsuarioByCorreoAndPass(
        @Query("correo") correo: String,
        @Query("pass") pass: String
    ): Response<Usuario>

    @GET("carteras")
    suspend fun getCarteraByNumCuenta(
        @Query("numCuenta") numCuenta: String
    ): Response<Cartera>

    @GET("usuarios/{correo}")
    suspend fun getUsuarioByCorreo(
        @Path("correo") correo: String
    ): Response<Usuario>

    @GET("usuarios/{id}/ingresos")
    suspend fun getIngresosByUsuario(
        @Path("id") id: Int
    ): Response<List<Ingreso>>

    @GET("usuarios/{id}/gastos")
    suspend fun getGastosByUsuario(
        @Path("id") id: Int
    ): Response<List<Gasto>>

    @GET("sectores/nombre/{nombre}")
    suspend fun getSectorByName(
        @Path("nombre") nombre: String
    ): Response<Sector>

    @POST("gastos")
    suspend fun addGasto(
        @Body gasto: Gasto
    ): Response<Gasto>

    @POST("ingresos")
    suspend fun addIngreso(
        @Body ingreso: Ingreso
    ): Response<Ingreso>

    @GET("usuarios/{id}/gastos/cantTotal")
    suspend fun getGastosTotalesByUsuario(
        @Path("id") usuarioId: Int
    ): Response<BigDecimal>

    @GET("usuarios/{id}/gastos/categoria/{categoria}/cantTotal")
    suspend fun getGastosTotalesByUsuarioAndCategoria(
        @Path("id") usuarioId: Int,
        @Path("categoria") nombre: String
    ): Response<BigDecimal>

    @GET("usuarios/{id}/totalCan")
    suspend fun getIngresosTotalesByUsuario(
        @Path("id") id: Int
    ): Response<BigDecimal>

    @PUT("usuarios/{id}")
    suspend fun updateUsuario(
        @Path("id") id: Int,
        @Body usuarioActualizado: Usuario?
    ): Response<Usuario>

    @DELETE("usuarios/{id}")
    suspend fun deleteUsuario(
        @Path("id") id: Int
    ): Response<Void>

    @DELETE("ingresos/{id}")
    suspend fun deleteIngresos(
        @Path("id") id: Int
    ): Response<Void>

    @DELETE("gastos/{id}")
    suspend fun deleteGastos(
        @Path("id") id: Int
    ): Response<Void>

    @DELETE("carteras/{id}")
    suspend fun deleteCartera(
        @Path("id") id: Int
    ): Response<Void>
}