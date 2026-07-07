package com.org.gestor_tareas.domain.repository

import com.org.gestor_tareas.domain.model.OrdenTrabajo

interface OrdenesRepository {

    // 1. Obtener todas las órdenes (La más importante)
    suspend fun getAllOrdenes(): List<OrdenTrabajo>

    // 2. Obtener órdenes por un estado específico (Filtro)
    // Útil para el Dashboard (ej: ver solo las "Pendientes")
    suspend fun getOrdenesByEstado(estado: String): List<OrdenTrabajo>

    // 3. Obtener una sola orden por su código o ID
    // Útil para ver el detalle de una orden específica
    suspend fun getOrdenByCodigo(codigo: String): OrdenTrabajo?
}