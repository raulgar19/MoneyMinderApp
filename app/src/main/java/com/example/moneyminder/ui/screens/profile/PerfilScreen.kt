package com.example.moneyminder.ui.screens.profile

import android.annotation.SuppressLint
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
    import androidx.compose.material.Text
    import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PerfilScreen(
    goBack: () -> Boolean,
    perfilViewModel: PerfilViewModel = hiltViewModel(),
    correo: String
) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Black) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                tint = Color.White,
                modifier = Modifier.clickable{
                    goBack()
                })
            Image(
                painterResource(R.mipmap.carga),
                contentDescription = "icono",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                "Perfil del usuario",
                color = Color.White,
            )
        }
    }) {
        BackgroundImage()
        perfilBody(perfilViewModel)
        LaunchedEffect(correo) {
            perfilViewModel.getUsuarioByCorreo(correo)
        }
    }
}

@Composable
fun perfilBody(
    perfilViewModel: PerfilViewModel = hiltViewModel()
) {
    val usuario = perfilViewModel.usuario
    Column(modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "icono usuario",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.padding(15.dp))
        if (usuario != null) {
            Text("Hola, " + usuario.nombre + " " + usuario.apellido, color = Color.White, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))
        if (usuario != null) {
            Text("Número de cuenta: " + usuario.cartera.numCuenta, color = Color.White, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))
        if (usuario != null) {
            Text("Saldo actual: " + usuario.cartera.dinero + " €", color = Color.White, fontSize = 20.sp)
        }
    }
}