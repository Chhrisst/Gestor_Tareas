package com.org.gestor_tareas.di

import com.org.gestor_tareas.domain.usecase.*

class UseCaseModule(repositoryModule: RepositoryModule) {
    val tecnicoUseCases by lazy {
        TecnicoUseCases(
            getTecnicos = GetTecnicosUseCase(repositoryModule.tecnicoRepository),
            addTecnico = AddTecnicoUseCase(repositoryModule.tecnicoRepository),
            updateTecnico = UpdateTecnicoUseCase(repositoryModule.tecnicoRepository),
            deleteTecnico = DeleteTecnicoUseCase(repositoryModule.tecnicoRepository)
        )
    }

    val loginUseCase by lazy {
        LoginUseCase(repositoryModule.authRepository)
    }

    val googleLoginUseCase by lazy {
        GoogleLoginUseCase(repositoryModule.authRepository)
    }

    val registerUseCase by lazy {
        RegisterUseCase(repositoryModule.authRepository)
    }
}
