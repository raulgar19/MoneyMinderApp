package com.example.moneyminder.di

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.moneyminder.datasource.MoneyMinderRepository
import com.example.moneyminder.datasource.MoneyMinderRepositoryImpl
import com.example.moneyminder.datasource.remote.MoneyMinderAPI
import com.example.moneyminder.datasource.remote.MoneyMinderRemoteDataSource
import com.example.moneyminder.datasource.remote.MoneyMinderRemoteDataSourceImpl
import com.example.moneyminder.usescases.add.cartera.AddCarteraUsecases
import com.example.moneyminder.usescases.add.cartera.AddCarteraUsecasesImpl
import com.example.moneyminder.usescases.add.gasto.AddGastoUsecases
import com.example.moneyminder.usescases.add.gasto.AddGastoUsecasesImpl
import com.example.moneyminder.usescases.add.ingreso.AddIngresoUsecases
import com.example.moneyminder.usescases.add.ingreso.AddIngresoUsecasesImpl
import com.example.moneyminder.usescases.add.usuario.AddUsuarioUsecases
import com.example.moneyminder.usescases.add.usuario.AddUsuarioUsecasesImpl
import com.example.moneyminder.usescases.delete.cartera.DeleteCarteraUsecases
import com.example.moneyminder.usescases.delete.cartera.DeleteCarteraUsecasesImpl
import com.example.moneyminder.usescases.delete.gasto.DeleteGastosUsecases
import com.example.moneyminder.usescases.delete.gasto.DeleteGastosUsecasesImpl
import com.example.moneyminder.usescases.delete.ingreso.DeleteIngresosUsecases
import com.example.moneyminder.usescases.delete.ingreso.DeleteIngresosUsecasesImpl
import com.example.moneyminder.usescases.delete.usuario.DeleteUsuarioUsecases
import com.example.moneyminder.usescases.delete.usuario.DeleteUsuarioUsecasesImpl
import com.example.moneyminder.usescases.get.cartera.GetCarteraUsecases
import com.example.moneyminder.usescases.get.cartera.GetCarteraUsecasesImpl
import com.example.moneyminder.usescases.get.gasto.GetGastosUsecases
import com.example.moneyminder.usescases.get.gasto.GetGastosUsecasesImpl
import com.example.moneyminder.usescases.get.ingreso.GetIngresosUsecases
import com.example.moneyminder.usescases.get.ingreso.GetIngresosUsecasesImpl
import com.example.moneyminder.usescases.get.sector.GetSectorUsecases
import com.example.moneyminder.usescases.get.sector.GetSectorUsecasesImpl
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecases
import com.example.moneyminder.usescases.get.usuario.GetUsuarioUsecasesImpl
import com.example.moneyminder.usescases.update.usuario.UpdateUsuarioUsecases
import com.example.moneyminder.usescases.update.usuario.UpdateUsuarioUsecasesImpl
import com.example.moneyminder.util.DispatcherProvider
import com.example.moneyminder.util.DispatcherProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            Handler(Looper.getMainLooper()).post {
                Log.d("SIGNALCALL ", request.toString())
            }
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {}.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @SuppressLint("SimpleDateFormat")
    @Provides
    fun provideGson(): Gson {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00")
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
        return GsonBuilder().serializeNulls().setLenient().setDateFormat(dateFormat.toPattern())
            .create()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder.protocols(mutableListOf(Protocol.HTTP_1_1)).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.62:8080")  // emulador = 10.0.2.2   casa = 192.168.1.62   parcela = 192.168.68.111  benidorm = 192.168.0.22
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }

    @Provides
    @Singleton
    fun provideMoneyMinderAPI(
        retrofit: Retrofit
    ): MoneyMinderAPI {
        return retrofit.create(MoneyMinderAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideMoneyMinderRemoteDataSource(
        moneyMinderAPI: MoneyMinderAPI, dispatcherProvider: DispatcherProvider
    ): MoneyMinderRemoteDataSource {
        return MoneyMinderRemoteDataSourceImpl(moneyMinderAPI, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideMoneyMinderRepository(
        moneyMinderRemoteDataSource: MoneyMinderRemoteDataSource
    ): MoneyMinderRepository {
        return MoneyMinderRepositoryImpl(moneyMinderRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetUsuarioUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): GetUsuarioUsecases {
        return GetUsuarioUsecasesImpl(moneyMinderRepository)
    }

    @Provides
    @Singleton
    fun provideAddUsuarioUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): AddUsuarioUsecases =
        AddUsuarioUsecasesImpl(moneyMinderRepository)


    @Provides
    @Singleton
    fun provideAddCarteraUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): AddCarteraUsecases =
        AddCarteraUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideGetCarteraUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): GetCarteraUsecases =
        GetCarteraUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideGetIngresosUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): GetIngresosUsecases =
        GetIngresosUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideAddGastoUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): AddGastoUsecases =
        AddGastoUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideGetSectorUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): GetSectorUsecases =
        GetSectorUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideGetGastosUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): GetGastosUsecases =
        GetGastosUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideAddIngresoUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): AddIngresoUsecases =
        AddIngresoUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideUpdateUsuarioUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): UpdateUsuarioUsecases =
        UpdateUsuarioUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideDeleteUsuarioUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): DeleteUsuarioUsecases =
        DeleteUsuarioUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideDeleteCarteraUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): DeleteCarteraUsecases =
        DeleteCarteraUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideDeleteIngresosUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): DeleteIngresosUsecases =
        DeleteIngresosUsecasesImpl(moneyMinderRepository)

    @Provides
    @Singleton
    fun provideDeleteGastosUsecases(
        moneyMinderRepository: MoneyMinderRepository
    ): DeleteGastosUsecases =
        DeleteGastosUsecasesImpl(moneyMinderRepository)
}