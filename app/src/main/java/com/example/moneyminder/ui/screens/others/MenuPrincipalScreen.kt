package com.example.moneyminder.ui.screens.others

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MenuPrincipalScreen(
    goIngresos: (String) -> Unit,
    goGastos: (String) -> Unit,
    goPerfil: (String) -> Unit,
    goAjustes: (String) -> Unit,
    correo: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "¡BIENVENIDO A MONEY MINDER!",
                        color = Color.White,
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            goIngresos(correo)
                        },
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Icon(Icons.Default.AttachMoney, contentDescription = "Ingresos", tint = Color.White)
                    }
                    IconButton(
                        onClick = {
                            goGastos(correo)
                        },
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Gastos", tint = Color.White)
                    }
                    IconButton(
                        onClick = {
                            goPerfil(correo)
                        },
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White)
                    }
                    IconButton(
                        onClick = {
                            goAjustes(correo)
                        },
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes", tint = Color.White)
                    }
                }
            }
        }
    ) {
        BackgroundImage()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(R.mipmap.carga),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    }
}