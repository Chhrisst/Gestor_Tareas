package com.org.gestor_tareas.di

import android.content.Context
import com.org.gestor_tareas.ui.pantallas.auth.LoginViewModel
import com.org.gestor_tareas.ui.pantallas.auth.RegisterViewModel

class AppContainer(private val context: Context) {
    val networkModule by lazy {
        NetworkModule()
    }

    private val repositoryModule by lazy {
        RepositoryModule(context, networkModule)
    }

    val useCaseModule by lazy {
        UseCaseModule(repositoryModule)
    }

    val tecnicoViewModel by lazy {
        com.org.gestor_tareas.ui.pantallas.tecnicos.TecnicoViewModel(useCaseModule.tecnicoUseCases)
    }

    val loginViewModel by lazy {
        LoginViewModel(useCaseModule.loginUseCase)
    }

    val registerViewModel by lazy {
        RegisterViewModel(useCaseModule.registerUseCase)
    }
}