package com.org.gestor_tareas.data.remote.dto


/**
 * Representa la orden tal cual viene del servidor (AWS).
 */
data class OrdenDto(
    val id: Int,
    val codigo: String,
    val cliente_nombre: String, // AWS suele usar snake_case
    val lugar: String,
    val hora_atencion: String,
    val estado: String
)

/**
 * Lo que enviamos a AWS para crear una nueva orden.
 * Nota que no enviamos el 'id' ni el 'codigo' porque AWS los genera.
 */
data class CreateOrdenRequest(
    val cliente_nombre: String,
    val lugar: String,
    val hora_atencion: String,
    val estado: String
)

/**
 * Lo que enviamos para actualizar una orden existente.
 */
data class UpdateOrdenRequest(
    val cliente_nombre: String,
    val lugar: String,
    val hora_atencion: String,
    val estado: String
)

/**
 * Clase para envolver la lista de órdenes si tu API devuelve un objeto 'data' o 'items'.
 */
data class OrdenesDataDto(
    val items: List<OrdenDto>
)