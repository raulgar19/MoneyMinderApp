package com.example.moneyminder.datasource

import com.example.moneyminder.datasource.remote.MoneyMinderRemoteDataSource
import com.example.moneyminder.ui.models.Cartera
import com.example.moneyminder.ui.models.Gasto
import com.example.moneyminder.ui.models.Ingreso
import com.example.moneyminder.ui.models.Sector
import com.example.moneyminder.ui.models.Usuario
import retrofit2.Response
import java.math.BigDecimal
import javax.inject.Inject

class MoneyMinderRepositoryImpl @Inject constructor(
    private val moneyMinderRemoteDataSource: MoneyMinderRemoteDataSource
) : MoneyMinderRepository {
    override suspend fun getUsuario(
        correo: String, pass: String
    ): Response<Usuario> {
        return moneyMinderRemoteDataSource.getUsuario(correo, pass)
    }

    override suspend fun addUsuario(
        nuevoUsuario: Usuario
    ): Response<Usuario> {
        return moneyMinderRemoteDataSource.addUsuario(nuevoUsuario)
    }

    override suspend fun addCartera(
        nuevaCartera: Cartera
    ): Response<Cartera> {
        return moneyMinderRemoteDataSource.addCartera(nuevaCartera)
    }

    override suspend fun getCartera(
        numCuenta: String
    ): Response<Cartera> {
        return moneyMinderRemoteDataSource.getCartera(numCuenta)
    }

    override suspend fun getUsuarioByCorreo(
        correo: String
    ): Response<Usuario> {
        return moneyMinderRemoteDataSource.getUsuarioByCorreo(correo)
    }

    override suspend fun getIngresosByUsuario(
        id: Int
    ): Response<List<Ingreso>> {
        return moneyMinderRemoteDataSource.getIngresosByUsuario(id)
    }

    override suspend fun getGastosByUsuario(
        id: Int
    ): Response<List<Gasto>> {
        return moneyMinderRemoteDataSource.getGastosByUsuario(id)
    }

    override suspend fun getSectorByName(
        nombre: String
    ): Response<Sector> {
        return moneyMinderRemoteDataSource.getSectorByName(nombre)
    }

    override suspend fun addGasto(
        gasto: Gasto
    ): Response<Gasto> {
        return moneyMinderRemoteDataSource.addGasto(gasto)
    }

    override suspend fun addIngreso(
        ingreso: Ingreso
    ): Response<Ingreso> {
        return moneyMinderRemoteDataSource.addIngreso(ingreso)
    }

    override suspend fun getGastosTotalesByUsuario(
        usuarioId: Int
    ): Response<BigDecimal> {
        return moneyMinderRemoteDataSource.getGastosTotalesByUsuario(usuarioId)
    }

    override suspend fun getGastosTotalesByUsuarioAndCategoria(
        usuarioId: Int,
        nombre: String
    ): Response<BigDecimal> {
        return moneyMinderRemoteDataSource.getGastosTotalesByUsuarioAndCategoria(usuarioId, nombre)
    }

    override suspend fun getIngresosTotalesByUsuario(
        id: Int
    ): Response<BigDecimal> {
        return moneyMinderRemoteDataSource.getIngresosTotalesByUsuario(id)
    }

    override suspend fun updateUsuario(
        id: Int,
        usuarioActualizado: Usuario?
    ): Response<Usuario> {
        return moneyMinderRemoteDataSource.updateUsuario(id, usuarioActualizado)
    }

    override suspend fun deleteUsuario(id: Int): Response<Void> {
        return moneyMinderRemoteDataSource.deleteUsuario(id)
    }

    override suspend fun deleteIngresos(
        id: Int
    ): Response<Void> {
        return moneyMinderRemoteDataSource.deleteIngresos(id)
    }

    override suspend fun deleteGastos(
        id: Int
    ): Response<Void> {
        return moneyMinderRemoteDataSource.deleteGastos(id)
    }

    override suspend fun deleteCartera(
        id: Int
    ): Response<Void> {
        return moneyMinderRemoteDataSource.deleteCartera(id)
    }
}