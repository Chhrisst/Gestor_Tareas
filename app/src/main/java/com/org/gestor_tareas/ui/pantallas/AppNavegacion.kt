package com.org.gestor_tareas.ui.pantallas

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.org.gestor_tareas.navigation.Rutas

@Composable
fun AppNavegacion(container: com.org.gestor_tareas.di.AppContainer) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Rutas.Inicio.ruta
    ) {
        composable(Rutas.Inicio.ruta) {
            InicioScreen(
                onIniciarSesionClick = { navController.navigate(Rutas.Login.ruta) },
                onRegistrarseClick = { navController.navigate(Rutas.Registro.ruta) }
            )
        }

        composable(Rutas.Login.ruta) {
            LoginScreen(
                viewModel = container.loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Rutas.Dashboard.ruta) {
                        popUpTo(Rutas.Inicio.ruta) { inclusive = true }
                    }
                },
                onRegistrateClick = { navController.navigate(Rutas.Registro.ruta) },
                onOlvidasteClick = { navController.navigate(Rutas.Olvidaste.ruta) }
            )
        }

        composable(Rutas.Registro.ruta) {
            RegisterScreen(
                viewModel = container.registerViewModel,
                onRegisterSuccess = {
                    Toast.makeText(context, "¡Registro exitoso! Por favor inicia sesión.", Toast.LENGTH_LONG).show()
                    navController.navigate(Rutas.Login.ruta) {
                        popUpTo(Rutas.Registro.ruta) { inclusive = true }
                    }
                },
                onIniciarSesionClick = { navController.navigate(Rutas.Login.ruta) }
            )
        }

        composable(Rutas.Olvidaste.ruta) {
            ForgotPasswordScreen(
                onEnviarClick = { email ->
                    Toast.makeText(context, "Instrucciones enviadas a: $email", Toast.LENGTH_LONG).show()
                    navController.navigate(Rutas.Login.ruta) {
                        popUpTo(Rutas.Login.ruta) { inclusive = true }
                    }
                },
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable(Rutas.Dashboard.ruta) {
            DashboardScreen(
                onVerTecnicosClick = {
                    navController.navigate("lista_tecnicos")
                },
                onVerOrdenesClick = {
                    Toast.makeText(context, "Pantalla de órdenes: pendiente de crear", Toast.LENGTH_SHORT).show()
                },
                onDelegarTareasClick = {
                    navController.navigate("asignar_trabajo")
                }
            )
        }

        composable("asignar_trabajo") {
            AsignarTrabajoScreen(
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable("lista_tecnicos") {
            TecnicoListScreen(
                navController = navController,
                viewModel = container.tecnicoViewModel
            )
        }
    }
}