package com.example.moneyminder.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AjustesScreen(
    goBack: () -> Unit,
    goUsuario: (String) -> Unit,
    goSecurity: (String) -> Unit,
    goCuenta: (String) -> Unit,
    goSoporte: () -> Unit,
    goAcerca: () -> Unit,
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
                    "Ajustes",
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
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ConfiguracionItem("Usuario", Icons.Default.AccountCircle) {
                    goUsuario(correo)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                ConfiguracionItem("Seguridad y Contraseñas", Icons.Default.Lock) {
                    goSecurity(correo)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                ConfiguracionItem("Cuenta", Icons.Default.Email) {
                   goCuenta(correo)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                ConfiguracionItem("Soporte y comentarios", Icons.Default.AccessibilityNew) {
                    goSoporte()
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White)
                ConfiguracionItem("Acerca de", Icons.Default.Info) {
                    goAcerca()
                }
            }
        }
    }
}

@Composable
fun ConfiguracionItem(text: String, icon: ImageVector, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.White)
    }
}