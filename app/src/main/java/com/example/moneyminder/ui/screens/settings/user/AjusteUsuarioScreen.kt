package com.example.moneyminder.ui.screens.settings.user

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage
import com.example.moneyminder.ui.screens.settings.ConfiguracionItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AjusteUsuarioScreen(
    goBack: () -> Unit,
    goUsuario: (String) -> Unit,
    goLogin: () -> Unit,
    ajusteUsuarioViewModel: AjusteUsuarioViewModel = hiltViewModel(),
    correo: String
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Black) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goBack()
                    }
                )
                Image(
                    painterResource(R.mipmap.carga),
                    contentDescription = "icono",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    "Configuración del usuario",
                    color = Color.White,
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            BackgroundImage()
            LaunchedEffect(correo) {
                ajusteUsuarioViewModel.getUsuarioByCorreo(correo)
            }
            var showConfirmationDialog by remember { mutableStateOf(false) }

            if (showConfirmationDialog) {
                AlertDialog(
                    backgroundColor = Color.Black,
                    onDismissRequest = { showConfirmationDialog = false },
                    title = { Text(text = "Eliminar usuario", color = Color.White) },
                    text = { Text(text = "¿Estás seguro de que deseas eliminar este usuario?", color = Color.White) },
                    confirmButton = {
                        Button(
                            onClick = {
                                ajusteUsuarioViewModel.deleteIngresos()
                                goLogin()
                                showConfirmationDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(text = "Confirmar", color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showConfirmationDialog = false },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(text = "Cancelar", color = Color.White)
                        }
                    }
                )
            } else {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    ConfiguracionItem("Actualizar perfil", Icons.Default.AccountCircle) {
                        goUsuario(correo)
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                    ConfiguracionItem("Borrar perfil", Icons.Default.AccountCircle) {
                        showConfirmationDialog = true
                    }
                }
            }
        }
    }
}