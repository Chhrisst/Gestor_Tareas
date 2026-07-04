package com.org.gestor_tareas.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import com.org.gestor_tareas.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val FondoIzquierda = Color(0xFF031A4C)
private val FondoDerecha = Color(0xFF0D110E)
private val AzulAcento = Color(0xFF2F6FED)
private val VerdeAcento = Color(0xFF2FB66B)
private val AmarilloAcento = Color(0xFFF2B33D)
private val VerdeEstado = Color(0xFF35D07F)
private val AmarilloEstado = Color(0xFFF2C84B)
private val RojoEstado = Color(0xFFEF5350)
private val TituloOscuro = Color(0xFF1A1A1A)
private val TextoOscuroSecundario = Color(0xFF6B6B6B)
private val TextoClaro = Color(0xFFC7CCD6)

@Composable
fun DashboardScreen(
    nombreAdministrador: String = "USUARIO",
    rolUsuario: String = "TECNICO",
    onVerTecnicosClick: () -> Unit = {},
    onVerOrdenesClick: () -> Unit = {},
    onDelegarTareasClick: () -> Unit = {}
) {
    val fechaFormateada = remember {
        val formato = SimpleDateFormat("EEEE, d 'de' MMMM yyyy", Locale("es", "ES"))
        formato.format(Date()).replaceFirstChar { it.uppercase() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(FondoIzquierda, FondoDerecha)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 56.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2A3A55)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Avatar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Hola, $nombreAdministrador",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Text(
                        text = "Rol: $rolUsuario | $fechaFormateada",
                        color = TextoClaro,
                        fontSize = 12.sp
                    )
                }
            }

            // SOLO EL ADMIN VE LA GESTIÓN DE TÉCNICOS
            if (rolUsuario == "ADMIN") {
                DashboardCard(
                    titulo = "Gestión de Técnicos",
                    iconoResId = R.drawable.icono_tecnicos,
                    colorAcento = AzulAcento,
                    textoBoton = "Ver Lista de Técnicos",
                    onBotonClick = onVerTecnicosClick
                ) {
                    Text(
                        text = "10 Técnicos Activos",
                        color = TituloOscuro,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    EstadoFila(color = VerdeEstado, texto = "7 en Trabajando")
                    EstadoFila(color = AmarilloEstado, texto = "2 en Espera de trabajo")
                    EstadoFila(color = RojoEstado, texto = "1 Fuera de Servicio")
                }
            }

            // AMBOS PUEDEN VER LAS ÓRDENES (O según decidan después)
            DashboardCard(
                titulo = "Estado de Órdenes",
                iconoResId = R.drawable.icono_ordenes,
                colorAcento = VerdeAcento,
                textoBoton = "Ver Órdenes de Trabajo",
                onBotonClick = onVerOrdenesClick
            ) {
                Text(
                    text = "24 Tareas Programadas",
                    color = TituloOscuro,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                EstadoFila(color = VerdeEstado, texto = "14 Finalizadas con Éxito")
                EstadoFila(color = AmarilloEstado, texto = "6 En Proceso de Ejecución")
                EstadoFila(color = RojoEstado, texto = "4 Retrasadas")
            }

            // SOLO EL ADMIN VE LA ASIGNACIÓN DE TRABAJO
            if (rolUsuario == "ADMIN") {
                DashboardCard(
                    titulo = "Asignación de Trabajo",
                    iconoResId = R.drawable.icono_asignacion,
                    colorAcento = AmarilloAcento,
                    textoBoton = "Delegar Tareas",
                    onBotonClick = onDelegarTareasClick
                ) {
                    Text(
                        text = "Asignar lugar de trabajo",
                        color = TextoOscuroSecundario,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun EstadoFila(color: Color, texto: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto, color = TextoOscuroSecundario, fontSize = 14.sp)
    }
}

@Composable
private fun DashboardCard(
    titulo: String,
    @DrawableRes iconoResId: Int,
    colorAcento: Color,
    textoBoton: String,
    onBotonClick: () -> Unit,
    contenido: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titulo,
                    color = TituloOscuro,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = iconoResId),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            contenido()
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = onBotonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorAcento)
            ) {
                Text(
                    text = textoBoton,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}