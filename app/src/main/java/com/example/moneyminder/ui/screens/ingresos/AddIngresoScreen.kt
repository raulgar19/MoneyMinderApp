package com.example.moneyminder.ui.screens.ingresos

import android.annotation.SuppressLint
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage
import java.math.BigDecimal

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddIngresoScreen(
    goBack: () -> Boolean,
    goIngresos: (String) -> Unit,
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
                    "Añadir ingreso",
                    color = Color.White,
                )
            }
        }){
        BackgroundImage()
        AddIngresoBody(goIngresos, correo)
    }
}

@Composable
fun AddIngresoBody(goIngresos: (String) -> Unit, correo: String, addIngresoViewModel: AddIngresoViewModel = hiltViewModel()) {
    val cantidad = addIngresoViewModel.cantidad

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Datos del ingreso",
            color =  Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            ))
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = cantidad.toString(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            onValueChange = {
                val parsedValue = it.toBigDecimalOrNull()
                addIngresoViewModel.cantidad = parsedValue ?: BigDecimal.ZERO
            },
            label = { Text("Cantidad", color = Color.Black) },
            modifier = Modifier
                .background(color = Color.White)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus()
                }
            ),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                addIngresoViewModel.getUsuarioByCorreo(correo, cantidad)
                goIngresos(correo)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text("Añadir Ingreso", color = Color.White)
        }
    }
}