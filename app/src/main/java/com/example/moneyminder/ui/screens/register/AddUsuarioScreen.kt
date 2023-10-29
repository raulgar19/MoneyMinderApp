package com.example.moneyminder.ui.screens.register

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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moneyminder.R
import com.example.moneyminder.ui.components.BackgroundImage
import java.math.BigDecimal

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddUsuarioScreen(
    goBack: () -> Boolean,
    goLogin: () -> Unit,
    goTerminos: () -> Unit,
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
                    "Crear cuenta",
                    color = Color.White,
                )
            }
        }
    ) {
        BackgroundImage()
        addUserBody(goLogin, goTerminos)
    }
}

@Composable
fun addUserBody(
    goLogin: () -> Unit,
    goTerminos: () -> Unit,
    addUsuarioScreenViewModel: AddUsuarioViewModel = hiltViewModel()
) {
    var isChecked = addUsuarioScreenViewModel.checkBox
    var isEnabled = addUsuarioScreenViewModel.isEnabled
    val isPasswordValid = addUsuarioScreenViewModel.pass.length >= 8
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(addUsuarioScreenViewModel.correo).matches()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddUserInfo(addUsuarioScreenViewModel, isPasswordValid)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { addUsuarioScreenViewModel.onCheckChange(it) },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.White
                )
            )
            Text(text = "Acepto los ", color = Color.White)
            ClickableText(
                text = AnnotatedString("Términos de Uso y Política de Privacidad"),
                style = TextStyle(
                    color = Color.LightGray,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    goTerminos()
                }
            )
        }

        Button(
            enabled = isEnabled && isPasswordValid && isEmailValid,
            onClick = {
                addUsuarioScreenViewModel.addCartera()
                goLogin()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text("Crear Cuenta", color = Color.White)
        }
    }
}

@Composable
fun AddUserInfo(addUsuarioViewModel: AddUsuarioViewModel, isPasswordValid: Boolean) {
    val nombre = addUsuarioViewModel.nombre
    val apellido = addUsuarioViewModel.apellido
    val correo = addUsuarioViewModel.correo
    val pass = addUsuarioViewModel.pass
    val numCuenta = addUsuarioViewModel.numCuenta
    val dinero = addUsuarioViewModel.dinero
    val passwordVisibility = remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Text(text = "Datos del usuario", style = TextStyle(color = Color.White))
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = nombre,
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
        onValueChange = { addUsuarioViewModel.nombre = it },
        label = { Text("Nombre", color = Color.Black) },
        modifier = Modifier.background(color = Color.White)
    )
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = apellido,
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
        onValueChange = { addUsuarioViewModel.apellido = it },
        label = { Text("Apellido", color = Color.Black) },
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester)
    )
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = correo,
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
        onValueChange = { addUsuarioViewModel.correo = it },
        label = { Text("Correo", color = Color.Black) },
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester)
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
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        onValueChange = { addUsuarioViewModel.pass = it },
        label = { Text("Contraseña", color = Color.Black) },
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester),
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
    if (!isPasswordValid) {
        Text(
            text = "La contraseña debe tener al menos 8 caracteres",
            color = Color.Red,
            style = TextStyle(fontSize = 13.sp),
            modifier = Modifier.padding(top = 4.dp, start = 8.dp)
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))
    Text(text = "Datos de la cuenta", style = TextStyle(color = Color.White))
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = numCuenta,
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
        onValueChange = { addUsuarioViewModel.numCuenta = it },
        label = { Text("Numero de cuenta", color = Color.Black) },
        modifier = Modifier
            .background(color = Color.White)
            .focusRequester(focusRequester)
    )
    Spacer(modifier = Modifier.padding(5.dp))
    OutlinedTextField(
        value = dinero.toString(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Black
        ),
        onValueChange = {
            val parsedValue = it.toBigDecimalOrNull()
            addUsuarioViewModel.dinero = parsedValue ?: BigDecimal.ZERO
        },
        label = { Text("Dinero", color = Color.Black) },
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
}