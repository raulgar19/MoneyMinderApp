package com.example.moneyminder.ui.screens.settings.user

import android.annotation.SuppressLint
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.MutableState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateUsuarioScreen(
    goBack: () -> Unit,
    goMenu: (String) -> Unit,
    updateUsuarioViewModel: UpdateUsuarioViewModel = hiltViewModel(),
    correo: String
){
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
                "Actualizar usuario",
                color = Color.White,
            )
        }
    }) {
        BackgroundImage()
        updateUsuarioBody(goMenu, correo, updateUsuarioViewModel)
    }
}

@Composable
fun updateUsuarioBody(goMenu: (String) -> Unit, correo: String, updateUsuarioViewModel: UpdateUsuarioViewModel) {
    updateUsuarioViewModel.getUsuarioByCorreo(correo)
    if (updateUsuarioViewModel.usuario != null) {
        val usuario = updateUsuarioViewModel.usuario!!
        val nombre = remember { mutableStateOf(TextFieldValue(usuario.nombre)) }
        val apellido = remember { mutableStateOf(TextFieldValue(usuario.apellido)) }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            updateUsuarioInfo(nombre, apellido)
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        updateUsuarioViewModel.updateUsuario(nombre.value.text, apellido.value.text)
                        goMenu(correo)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                ) {
                    Text("Actualizar", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun updateUsuarioInfo(
    nombre: MutableState<TextFieldValue>,
    apellido: MutableState<TextFieldValue>
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Text(text = "Datos del usuario", style = TextStyle(color = Color.White))
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = nombre.value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        onValueChange = { nombre.value = it },
        label = {Text("Nombre", color = Color.Black)},
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester)
    )
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = apellido.value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        onValueChange = { apellido.value = it },
        label = {Text("Apellido", color = Color.Black)},
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester)
    )
}