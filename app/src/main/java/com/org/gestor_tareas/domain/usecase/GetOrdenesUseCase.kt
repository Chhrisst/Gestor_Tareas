package com.org.gestor_tareas.domain.usecase

import com.org.gestor_tareas.domain.repository.OrdenesRepository

class GetOrdenesUseCase (private val repository: OrdenesRepository){
    suspend operator fun invoke() = repository.getAllOrdenes()
}