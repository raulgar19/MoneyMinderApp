package com.example.moneyminder.ui.screens.settings.pass

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
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PassScreen(
    passViewModel: PassViewModel = hiltViewModel(),
    goBack: () -> Boolean,
    goAjustePass: (String) -> Unit,
    correo: String
) {
    Scaffold(topBar = {
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
                "Acceso a contraseña",
                color = Color.White,
            )
        }
    }) {
        BackgroundImage()
        PassScreenBody(goAjustePass, passViewModel, correo)
    }
}


@Composable
fun PassScreenBody(goAjustePass: (String) -> Unit, passViewModel: PassViewModel, correo: String) {
    val pass = remember { mutableStateOf("") }
    val usuario = passViewModel.usuario
    val passwordVisibility = remember { mutableStateOf(false) }
    val showError = passViewModel.showError

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verificar contraseña",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        OutlinedTextField(
            value = pass.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            onValueChange = { pass.value = it },
            label = { Text("Contraseña", color = Color.Black) },
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .background(color = Color.White)
                .focusRequester(focusRequester),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility.value = !passwordVisibility.value }
                ) {
                    val icon =
                        if (passwordVisibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    Icon(
                        icon,
                        contentDescription = "Toggle Password Visibility",
                        tint = Color.Gray
                    )
                }
            }
        )
        LaunchedEffect(correo){
            passViewModel.getUsuario(correo)
        }
        if (showError) {
            Snackbar(
                backgroundColor = Color.DarkGray
            ) {
                Text(
                    text = "La contraseña es incorrecta. Inténtelo de nuevo",
                    color = Color.White
                )
            }
        }
        Button(
            onClick = {
                val userPassword = usuario?.pass ?: ""
                if (pass.value == userPassword) {
                    goAjustePass(usuario!!.correo)
                } else {
                    passViewModel.showError = true
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text(text = "Verificar", color = Color.White)
        }
    }
}