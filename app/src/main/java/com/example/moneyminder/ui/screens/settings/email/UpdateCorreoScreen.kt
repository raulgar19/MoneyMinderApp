package com.example.moneyminder.ui.screens.settings.email

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateCorreoScreen(
    goBack: () -> Boolean,
    goLogin: () -> Unit,
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
            Text(text = "Actualizar correo", color = Color.White)
        }
    }) {
        BackgroundImage()
        UpdateCorreoBody(goLogin,correo)
    }
}

@Composable
fun UpdateCorreoBody(
    goLogin: () -> Unit,
    correo: String,
    updateCorreoViewModel: UpdateCorreoViewModel = hiltViewModel()
) {
    LaunchedEffect(correo) {
        updateCorreoViewModel.getUsuarioByCorreo(correo)
    }

    val correoState = remember { mutableStateOf(TextFieldValue("")) }
    val isValidCorreo = correoState.value.text.isNotBlank() && correoState.value.text.contains("@")

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Introduce el nuevo correo",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 15.dp)
        )
        OutlinedTextField(
            value = correoState.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            onValueChange = {
                correoState.value = it
            },
            label = { Text("Correo", color = Color.Black) },
            modifier = Modifier
                .background(color = Color.White)
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                if (isValidCorreo) {
                    updateCorreoViewModel.updateUsuario(correoState.value.text)
                    goLogin()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            enabled = isValidCorreo
        ) {
            Text(text = "Actualizar", color = Color.White)
        }
    }
}
