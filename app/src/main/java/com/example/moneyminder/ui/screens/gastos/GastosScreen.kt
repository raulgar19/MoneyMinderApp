package com.example.moneyminder.ui.screens.gastos

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage
import com.example.moneyminder.ui.models.Gasto
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GastosScreen(
    gastosViewModel: GastosViewModel = hiltViewModel(),
    goMenu: (String) -> Unit,
    goAddGasto: (String) -> Unit,
    goGraf : (String) -> Unit,
    correo: String
) {
    val gastos = gastosViewModel.gastos ?: emptyList()
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Black) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        goMenu(correo)
                    }
                )
                Image(
                    painterResource(id = R.mipmap.carga),
                    contentDescription = "icono",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Gastos",
                    color = Color.White,
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Black,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        goAddGasto(correo)
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Añadir ingreso",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        goGraf(correo)
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icono),
                        contentDescription = "Icono personalizado",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) {
        BackgroundImage()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LaunchedEffect(correo) {
                gastosViewModel.getUsuarioByCorreo(correo)
            }

            if (gastos.isNotEmpty()) {
                GastosScreenBody(gastos)
            } else {
                Snackbar(
                    backgroundColor = Color.DarkGray,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text(
                        text = "Todavía no hay gastos registrados",
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    )
                        Spacer(modifier = Modifier.padding(5.dp))
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(16.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GastosScreenBody(gastos: List<Gasto>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cantidad",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            Text(
                text = "Fecha",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            Text(
                text = "Categoría",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(gastos) { gasto ->
                    GastosScreenInfo(gasto = gasto)
                }
            }
        }
    }
}

@Composable
fun GastosScreenInfo(gasto: Gasto) {
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    val formattedDate = remember { dateFormat.format(gasto.fecha) }
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "" + gasto.cantidad + " €", color = Color.White)
        Spacer(modifier = Modifier.padding(horizontal = 15.dp))
        Text(text = formattedDate, color = Color.White)
        Spacer(modifier = Modifier.padding(horizontal = 15.dp))
        Text(text = gasto.sector.nombre, color = Color.White)
    }
}
