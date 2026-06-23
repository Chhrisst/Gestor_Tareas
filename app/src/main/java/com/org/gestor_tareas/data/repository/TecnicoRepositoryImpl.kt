package com.org.gestor_tareas.data.repository

import com.org.gestor_tareas.data.mapper.toDomain
import com.org.gestor_tareas.data.remote.datasource.TecnicoRemoteDataSource
import com.org.gestor_tareas.data.remote.dto.CreateTecnicoRequest
import com.org.gestor_tareas.data.remote.dto.UpdateTecnicoRequest
import com.org.gestor_tareas.domain.model.Tecnico
import com.org.gestor_tareas.domain.repository.TecnicoRepository

class TecnicoRepositoryImpl(private val remoteDataSource: TecnicoRemoteDataSource) : TecnicoRepository {

    override suspend fun getTecnicos(): List<Tecnico> {
        return remoteDataSource.getTecnicos()
            ?.data
            ?.items
            ?.map { it.toDomain() }
            ?: emptyList()
    }

    override suspend fun addTecnico(nombre: String, rol: String) {
        remoteDataSource.addTecnico(CreateTecnicoRequest(nombre, rol))
    }

    override suspend fun updateTecnico(id: Int, nombre: String, rol: String) {
        remoteDataSource.updateTecnico(id, UpdateTecnicoRequest(nombre, rol))
    }

    override suspend fun deleteTecnico(id: Int) {
        remoteDataSource.deleteTecnico(id)
    }
}