@file:OptIn(ExperimentalMaterialApi::class)

package com.example.moneyminder.ui.screens.gastos

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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
fun AddGastoScreen(
    goBack: () -> Boolean,
    goGastos: (String) -> Unit,
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
                    "Añadir gasto",
                    color = Color.White,
                )
            }
        }){
        BackgroundImage()
        AddGastoBody(goGastos, correo)
    }
}

@Composable
fun AddGastoBody(goGastos: (String) -> Unit, correo: String, addGastoViewModel: AddGastoViewModel = hiltViewModel()) {
    val cantidad = addGastoViewModel.cantidad
    var isExpanded by remember { mutableStateOf(false) }
    var categoria by remember { mutableStateOf("") }

    val isReadyToCreate = cantidad > BigDecimal.ZERO && categoria.isNotBlank()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Datos del gasto",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = cantidad.toString(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            onValueChange = {
                val parsedValue = it.toBigDecimalOrNull()
                addGastoViewModel.cantidad = parsedValue ?: BigDecimal.ZERO
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
        Text(
            text = "Categoría",
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    IconToggleButton(
                        checked = isExpanded,
                        onCheckedChange = { isExpanded = it },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        val vector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

                        Icon(
                            painter = rememberVectorPainter(vector),
                            contentDescription = "Dropdown Icon",
                            tint = Color.Black
                        )
                    }
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(color = Color.DarkGray)
            ) {
                DropdownMenuItem(
                    onClick = {
                        categoria = "Transporte"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Transporte", color = Color.White)
                }
                DropdownMenuItem(
                    onClick = {
                        categoria = "Alimentación"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Alimentación", color = Color.White)
                }
                DropdownMenuItem(
                    onClick = {
                        categoria = "Ocio"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Ocio", color = Color.White)
                }
                DropdownMenuItem(
                    onClick = {
                        categoria = "Luz"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Luz", color = Color.White)
                }
                DropdownMenuItem(
                    onClick = {
                        categoria = "Agua"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Agua", color = Color.White)
                }
                DropdownMenuItem(
                    onClick = {
                        categoria = "Hipoteca"
                        isExpanded = false
                    }
                ) {
                    Text(text = "Hipoteca", color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                addGastoViewModel.getUsuarioByCorreo(correo, categoria, cantidad)
                goGastos(correo)
            },
            enabled = isReadyToCreate,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text("Añadir Gasto", color = Color.White)
        }
    }
}