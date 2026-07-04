package com.org.gestor_tareas.core.network

import com.org.gestor_tareas.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("tecnicos")
    suspend fun getTecnicos(): Response<ApiResponse<TecnicosDataDto>>

    @POST("tecnicos")
    suspend fun addTecnico(@Body request: CreateTecnicoRequest): Response<TecnicoDto>

    @PUT("tecnicos/{id}")
    suspend fun updateTecnico(@Path("id") id: Int, @Body request: UpdateTecnicoRequest): Response<TecnicoDto>

    @DELETE("tecnicos/{id}")
    suspend fun deleteTecnico(@Path("id") id: Int): Response<Unit>

    @POST("auth")
    suspend fun autenticar(@Body request: AuthRequest): Response<ApiResponse<AuthResponse>>

    @POST("auth")
    suspend fun googleLogin(@Body request: GoogleLoginRequest): Response<ApiResponse<AuthResponse>>

    @POST("auth")
    suspend fun verificarSesion(@Body request: VerificarSesionRequest): Response<ApiResponse<SessionStatusResponse>>
}
