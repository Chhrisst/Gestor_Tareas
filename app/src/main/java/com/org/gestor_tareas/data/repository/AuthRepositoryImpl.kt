package com.org.gestor_tareas.data.repository

import com.org.gestor_tareas.core.network.ApiService
import com.org.gestor_tareas.data.local.TokenDataStore
import com.org.gestor_tareas.data.remote.dto.AuthRequest
import com.org.gestor_tareas.data.remote.dto.AuthResponse
import com.org.gestor_tareas.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {
    override suspend fun autenticar(request: AuthRequest): Response<AuthResponse> {
        return apiService.autenticar(request)
    }

    override suspend fun guardarDatosAutenticacion(token: String, rol: String) {
        tokenDataStore.saveAuthData(token, rol)
    }
}
