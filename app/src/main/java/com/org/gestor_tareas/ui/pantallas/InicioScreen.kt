package com.org.gestor_tareas.ui.pantallas

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.org.gestor_tareas.R
import com.org.gestor_tareas.core.utils.Constants
import com.org.gestor_tareas.core.utils.iniciarSesionConGoogle
import kotlinx.coroutines.launch

@Composable
fun InicioScreen(
    loginViewModel: LoginViewModel,
    onIniciarSesionClick: () -> Unit = {},
    onRegistrarseClick: () -> Unit = {},
    onGoogleLoginSuccess: (String, String) -> Unit = { _, _ -> }
) {
    val colorBotonOscuro = Color(0xFF142B59)
    val colorTextoSecundario = Color(0xFFA0A0A0)
    
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState.error) {
        uiState.error?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.statusBarsPadding())
            
            Spacer(modifier = Modifier.weight(0.7f))

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.statusBarsPadding())
            Spacer(modifier = Modifier.weight(0.7f))
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.fillMaxWidth().height(130.dp), contentScale = ContentScale.Fit)
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = onIniciarSesionClick, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = colorBotonOscuro, contentColor = Color.White), shape = RoundedCornerShape(28.dp), border = BorderStroke(1.dp, Color.White)) {
                Text("INICIAR SESIÓN", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onRegistrarseClick, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = colorBotonOscuro), shape = RoundedCornerShape(28.dp)) {
                Text("REGISTRARSE", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Continuar con", color = colorTextoSecundario, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google), 
                    contentDescription = "Google", 
                    modifier = Modifier
                        .size(56.dp) // Un poco más grande al ser el único
                        .clickable {
                            scope.launch {
                                iniciarSesionConGoogle(
                                    context = context,
                                    webClientId = Constants.GOOGLE_WEB_CLIENT_ID,
                                    onSuccess = { idToken ->
                                        Log.d("GoogleAuth", "ID Token obtenido: $idToken")
                                        Toast.makeText(context, "Google Login Exitoso", Toast.LENGTH_SHORT).show()
                                        // TODO: Enviar idToken al ViewModel para autenticación con el backend
                                    },
                                    onError = { error ->
                                        Log.e("GoogleAuth", "Error: $error")
                                        Toast.makeText(context, "Error: $error", Toast.LENGTH_LONG).show()
                                    }
                                )
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.weight(0.5f))
            
            // Añadimos padding solo al final del contenido, no al fondo
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}
