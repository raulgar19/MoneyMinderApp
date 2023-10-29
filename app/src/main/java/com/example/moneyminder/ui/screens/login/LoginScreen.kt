package com.example.moneyminder.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    goMenuPrincipal: (String) -> Unit,
    goAddUsuario: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Black) {
            Image(
                painter = painterResource(R.mipmap.carga),
                contentDescription = "icono",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                "Iniciar sesión",
                color = Color.White
            )
        }
    }) {
        BackgroundImage()
        BodyContentLogin(goMenuPrincipal = goMenuPrincipal, goAddUsuario = goAddUsuario)
    }
}

@Composable
fun BodyContentLogin(
    goMenuPrincipal: (String) -> Unit,
    goAddUsuario: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val usuario = loginViewModel.usuario
    val showError = loginViewModel.showError
    val usuarioEncontrado = loginViewModel.usuarioEncontrado

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginInfo(loginViewModel = loginViewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "¿No tienes cuenta?", color = Color.White)
            Spacer(modifier = Modifier.padding(5.dp))
            ClickableText(
                text = AnnotatedString("Créate una"),
                style = TextStyle(
                    color = Color.LightGray,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    goAddUsuario()
                }
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))

        if (showError) {
            Snackbar(
                backgroundColor = Color.DarkGray
            ) {
                Text(
                    text = "Error al iniciar sesión. Vuelva a intentarlo.",
                    color = Color.White
                )
            }
        }

        Button(
            onClick = {
                loginViewModel.getUsuario()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text(text = "Iniciar Sesión", color = Color.White)
        }
        LaunchedEffect(usuarioEncontrado){
            if (usuarioEncontrado) {
                if (usuario != null) {
                    goMenuPrincipal(usuario.correo)
                }
            }
        }
    }
}


@Composable
fun LoginInfo(loginViewModel: LoginViewModel) {
    val correo = loginViewModel.correo
    val pass = loginViewModel.pass
    val passwordVisibility = remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = correo,
        onValueChange = { loginViewModel.correo = it },
        label = { Text("Correo", color = Color.Black) },
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black
        )
    )
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = pass,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        onValueChange = { loginViewModel.pass = it },
        label = { Text("Contraseña", color = Color.Black) },
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        modifier = Modifier.background(color = Color.White),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility.value = !passwordVisibility.value }
            ) {
                val icon = if (passwordVisibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff
                Icon(
                    icon,
                    contentDescription = "Toggle Password Visibility",
                    tint = Color.Gray
                )
            }
        }
    )
}