package com.org.gestor_tareas.navigation

sealed class Rutas(val ruta: String) {
    object Inicio : Rutas("inicio")
    object Login : Rutas("login")
    object Registro : Rutas("registro")
    object Olvidaste : Rutas("olvidaste")
    object Dashboard : Rutas("dashboard")
    object AsignarTrabajo : Rutas("asignar_trabajo")
    object ListaTecnicos : Rutas("lista_tecnicos")
}