package com.org.gestor_tareas.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignarTrabajoScreen(
    onVolverClick: () -> Unit
) {
    var nombreTecnico by remember { mutableStateOf("") }
    var detalleServicio by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var cliente by remember { mutableStateOf("") }

    val context = LocalContext.current
    val colorBotonCyan = Color(0xFF1396A9)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asignar Trabajo", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            CampoFormulario(titulo = "Nombre del Tecnico", valor = nombreTecnico, onValorCambia = { nombreTecnico = it })
            CampoFormulario(titulo = "Detalle del Servicio", valor = detalleServicio, onValorCambia = { detalleServicio = it }, maxLines = 3)
            CampoFormulario(titulo = "Dirección", valor = direccion, onValorCambia = { direccion = it })
            CampoFormulario(titulo = "Cliente", valor = cliente, onValorCambia = { cliente = it })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (nombreTecnico.isBlank() || detalleServicio.isBlank() || direccion.isBlank() || cliente.isBlank()) {
                        Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Trabajo asignado con éxito a $nombreTecnico", Toast.LENGTH_LONG).show()

                        nombreTecnico = ""
                        detalleServicio = ""
                        direccion = ""
                        cliente = ""

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorBotonCyan)
            ) {
                Text(text = "Agregar", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CampoFormulario(
    titulo: String,
    valor: String,
    onValorCambia: (String) -> Unit,
    maxLines: Int = 1
) {
    Column {
        Text(text = titulo, fontSize = 14.sp, color = Color.Black, modifier = Modifier.padding(bottom = 6.dp))
        OutlinedTextField(
            value = valor,
            onValueChange = onValorCambia,
            modifier = Modifier.fillMaxWidth(),
            singleLine = maxLines == 1,
            maxLines = maxLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1396A9),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color(0xFFF9F9F9),
                unfocusedContainerColor = Color(0xFFF9F9F9)
            )
        )
    }
}