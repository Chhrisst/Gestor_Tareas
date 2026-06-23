package com.org.gestor_tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.org.gestor_tareas.di.AppContainer
import com.org.gestor_tareas.ui.pantallas.AppNavegacion

class MainActivity : ComponentActivity() {
    private lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        container = (application as GestorTareasApplication).container

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                AppNavegacion(container)
            }
        }
    }
}