# 📱 Gestor de Tareas (Field Service App)

Gestor de Tareas es una aplicación nativa para Android desarrollada en **Kotlin** diseñada para la gestión de servicios en campo. Permite la administración de técnicos, visualización del estado de órdenes de trabajo, y asignación de tareas de manera eficiente.

## 🚀 Características Principales

* **Autenticación Segura:** Inicio de sesión y registro de usuarios, respaldado por un backend en AWS (incluyendo Single Sign-On con Google).
* **Gestión de Técnicos:** Panel CRUD (Crear, Leer, Actualizar, Eliminar) para administrar el personal en campo.
* **Asignación de Trabajos:** Formularios para detallar y asignar servicios, direcciones y clientes a los técnicos disponibles.
* **Dashboard Interactivo:** Vista general del estado de las operaciones y métricas clave.

## 🛠️ Stack Tecnológico

El proyecto está construido siguiendo los principios de **Clean Architecture** y el patrón de diseño **MVVM (Model-View-ViewModel)**.

* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI declarativa moderna.
* **Navegación:** `navigation-compose` para el enrutamiento entre pantallas.
* **Red:** [Retrofit2](https://square.github.io/retrofit/) y Gson para consumo de API REST.
* **Asincronismo:** Kotlin Coroutines & Flow (`StateFlow` / `SharedFlow`).
* **Almacenamiento Local:** [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (Preferences DataStore) para el manejo seguro de tokens de sesión.
* **Inyección de Dependencias:** Contenedor manual (`AppContainer`).
* **Backend:** Servicios en AWS (incluyendo funciones Lambda como `tecofield-auth-service`).

## 📁 Estructura del Proyecto

```
> app/src/main/java/com/org/gestor_tareas/
> ├── core/         # Utilidades generales, constantes y configuración base de red.
> ├── data/         # Repositorios, fuentes de datos (Remote/Local) y Mappers.
> ├── di/           # Contenedores y módulos de Inyección de Dependencias.
> ├── domain/       # Lógica de negocio, Modelos y Casos de Uso (Use Cases).
> ├── navigation/   # Rutas y configuración de navegación de la app.
> └── ui/           # Pantallas (Screens), ViewModels y estados de vista (UiState).
```

## ⚙️ Configuración y Ejecución

1. Clona este repositorio:
   ```
   > git clone https://github.com/tu-usuario/Gestor_Tareas.git
   ```

3. Abre el proyecto en **Android Studio**.
4. Sincroniza el proyecto con Gradle para descargar todas las dependencias.
5. *Nota:* Asegúrate de configurar las variables de entorno necesarias o verificar la URL de la API en `core/utils/Constants.kt` antes de compilar.
6. Ejecuta la aplicación en un emulador o dispositivo físico.

---

## 🔄 Guía de Trabajo Colaborativo (Git)

Para mantener las ramas individuales (`dev`, `rama-christian`, `rama-favian`, `rama-fernando`, `rama-sebastian`) sincronizadas con la rama principal (`main`) y recibir los últimos archivos integrados por el equipo, sigue este flujo en **Android Studio**:

### 1. Descargar los cambios (Fetch)
* Presiona **`Ctrl + T`** (o ve al menú superior de Git y selecciona **Update Project**).
* *Nota:* Esto solo descarga la información más reciente de GitHub para que Android Studio la detecte, pero no modifica tu código actual.

### 2. Fusionar los cambios (Merge)``
* Abre el menú desplegable de ramas en la parte superior derecha (donde aparece el nombre de tu rama actual).
* Desplázate hacia abajo hasta la sección **Remote** (o expande la carpeta `origin`).
* Haz clic sobre la rama **`origin/main`**.
* En el submenú que aparece, selecciona la opción **"Merge 'origin/main' into '[nombre-de-tu-rama]'"**.

### 3. Subir la actualización a GitHub (Push)
* Presiona **`Ctrl + Mayús + K`** (o ve a la barra superior Git > **Push...**).
* Haz clic en el botón azul **Push** para subir esta actualización a tu rama en la nube. ¡Tu rama ahora está sincronizada!

> **⚠️ Solución de errores: "Committer identity unknown"**
>
> Si al intentar hacer el paso 2 (Merge) la consola muestra un error indicando que Git no conoce tu identidad, debes configurarla por única vez. Abre la pestaña **Terminal** en la parte inferior de Android Studio y ejecuta estos dos comandos:
>
> `git config --global user.name "Tu Nombre y Apellido"`
>
> `git config --global user.email "tu_correo@ejemplo.com"`
>
> *Una vez configurado, vuelve a intentar el Paso 2.*

---

## 👥 Equipo de Desarrollo
* Christian Beteta
* Favian
* Fernando
* Sebastian
