package com.org.gestor_tareas.data.remote.dto

data class AuthResponse(
    val token: String,
    val nombre: String,
    val email: String
)
