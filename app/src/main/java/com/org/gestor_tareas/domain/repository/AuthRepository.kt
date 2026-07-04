package com.org.gestor_tareas.domain.repository

import com.org.gestor_tareas.data.remote.dto.AuthRequest
import com.org.gestor_tareas.data.remote.dto.AuthResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun autenticar(request: AuthRequest): Response<AuthResponse>
    suspend fun guardarDatosAutenticacion(token: String, rol: String)
}
