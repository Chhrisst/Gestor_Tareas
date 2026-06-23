package com.org.gestor_tareas.domain.usecase

import com.org.gestor_tareas.data.remote.dto.AuthRequest
import com.org.gestor_tareas.data.remote.dto.AuthResponse
import com.org.gestor_tareas.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(nombre: String, email: String, password: String): Result<AuthResponse> {
        return try {
            val response = repository.autenticar(AuthRequest("registro", email, password, nombre))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en el registro: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
