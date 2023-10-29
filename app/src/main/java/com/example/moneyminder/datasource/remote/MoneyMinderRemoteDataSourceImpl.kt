package com.example.moneyminder.datasource.remote

import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Sector
import com.example.moneyminder.ui.models.Usuario
import com.example.moneyminder.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.math.BigDecimal
import javax.inject.Inject

class MoneyMinderRemoteDataSourceImpl @Inject constructor(
    private val moneyMinderAPI: MoneyMinderAPI,
    private val dispatcherProvider: DispatcherProvider
) : MoneyMinderRemoteDataSource {
    override suspend fun getUsuario(
        correo: String, pass: String
    ): Response<Usuario> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getUsuarioByCorreoAndPass(correo, pass)
        }

    override suspend fun addUsuario(
        nuevoUsuario: Usuario
    ): Response<Usuario> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.addUsuario(nuevoUsuario)
    }

    override suspend fun addCartera(
        nuevaCartera: Cartera
    ): Response<Cartera> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.addCartera(nuevaCartera)
    }

    override suspend fun getCartera(
        numCuenta: String
    ): Response<Cartera> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getCarteraByNumCuenta(numCuenta)
        }

    override suspend fun getUsuarioByCorreo(
        correo: String
    ): Response<Usuario> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getUsuarioByCorreo(correo)
        }

    override suspend fun getIngresosByUsuario(
        id: Int
    ): Response<List<Ingreso>> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getIngresosByUsuario(id)
        }

    override suspend fun getGastosByUsuario(
        id: Int
    ): Response<List<Gasto>> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getGastosByUsuario(id)
        }

    override suspend fun getSectorByName(
        nombre: String
    ): Response<Sector> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getSectorByName(nombre)
        }

    override suspend fun addGasto(
        gasto: Gasto
    ): Response<Gasto> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.addGasto(gasto)
        }

    override suspend fun addIngreso(
        ingreso: Ingreso
    ): Response<Ingreso> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.addIngreso(ingreso)
        }

    override suspend fun getGastosTotalesByUsuario(
        usuarioId: Int
    ): Response<BigDecimal> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getGastosTotalesByUsuario(usuarioId)
        }

    override suspend fun getGastosTotalesByUsuarioAndCategoria(
        usuarioId: Int,
        nombre: String
    ): Response<BigDecimal> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
        }

    override suspend fun getIngresosTotalesByUsuario(
        id: Int
    ): Response<BigDecimal> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.getIngresosTotalesByUsuario(id)
        }

    override suspend fun updateUsuario(
        id: Int,
        usuarioActualizado: Usuario?
    ): Response<Usuario> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.updateUsuario(id, usuarioActualizado)
        }

    override suspend fun deleteUsuario(
        id: Int
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.deleteUsuario(id)
        }

    override suspend fun deleteIngresos(
        id: Int
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.deleteIngresos(id)
        }

    override suspend fun deleteGastos(
        id: Int
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.deleteGastos(id)
        }

    override suspend fun deleteCartera(
        id: Int
    ): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext moneyMinderAPI.deleteCartera(id)
        }
}