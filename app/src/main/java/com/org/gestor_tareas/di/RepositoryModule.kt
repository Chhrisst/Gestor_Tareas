package com.org.gestor_tareas.di

import android.content.Context
import com.org.gestor_tareas.data.local.TokenDataStore
import com.org.gestor_tareas.data.remote.datasource.TecnicoRemoteDataSource
import com.org.gestor_tareas.data.repository.AuthRepositoryImpl
import com.org.gestor_tareas.data.repository.TecnicoRepositoryImpl
import com.org.gestor_tareas.domain.repository.AuthRepository
import com.org.gestor_tareas.domain.repository.TecnicoRepository

class RepositoryModule(private val context: Context, networkModule: NetworkModule) {
    private val remoteDataSource by lazy {
        TecnicoRemoteDataSource(networkModule.apiService)
    }

    val tecnicoRepository: TecnicoRepository by lazy {
        TecnicoRepositoryImpl(remoteDataSource)
    }

    private val tokenDataStore by lazy {
        TokenDataStore(context)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(networkModule.apiService, tokenDataStore)
    }
}
