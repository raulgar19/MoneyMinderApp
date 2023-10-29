package com.example.moneyminder.ui.screens.settings.email

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage
import com.example.moneyminder.ui.screens.settings.ConfiguracionItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AjusteCuentaScreen(
    goBack: () -> Boolean,
    goCorreo: (String) -> Unit,
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
                    "Configuración del correo",
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
                ConfiguracionItem("Actualizar Correo", Icons.Default.AccountCircle) {
                    goCorreo(correo)
                }
            }
        }
    }
}