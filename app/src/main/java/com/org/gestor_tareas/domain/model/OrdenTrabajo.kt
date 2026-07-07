package com.org.gestor_tareas.domain.model

data class OrdenTrabajo (
    val id:Int,
    val codigo: String,
    val clienteNombre: String,
    val lugar: String,
    val horaAtencion: String,
    val estado: String
)