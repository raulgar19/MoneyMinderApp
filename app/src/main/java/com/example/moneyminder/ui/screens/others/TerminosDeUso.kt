package com.example.moneyminder.ui.screens.others

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun TerminosScreen(
   goBack: () -> Unit
){
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
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    "Términos de Uso y Política de Privacidad",
                    color = Color.White,
                )
            }
        }
    ) {
        BackgroundImage()
        TerminosScreenBody(goBack)
    }
}

@Composable
fun TerminosScreenBody(goBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido/a a nuestra aplicación. Antes de utilizar nuestra plataforma, te pedimos que leas detenidamente estos Términos y Condiciones de Uso y Privacidad (\"Términos\"). Al utilizar nuestra aplicación, estás aceptando cumplir con estos Términos y todas las leyes y regulaciones aplicables. Si no estás de acuerdo con alguno de los términos establecidos a continuación, te pedimos que no utilices nuestra aplicación.\n" +
                "\n" +
                "Recopilación y Uso de Datos\n" +
                "a. Al utilizar nuestra aplicación, podríamos recopilar ciertos datos personales, como tu nombre, dirección de correo electrónico y otra información relevante necesaria para brindarte nuestros servicios de manera efectiva. Nos comprometemos a utilizar tus datos de forma segura y confidencial, y solo con fines legítimos relacionados con la gestión de la aplicación.\n" +
                "\n" +
                "b. Podríamos recopilar información adicional sobre tu actividad en la aplicación, como el uso de funciones específicas y la interacción con otros usuarios. Estos datos se utilizarán para mejorar nuestra aplicación y personalizar tu experiencia de usuario.\n" +
                "\n" +
                "c. No compartiremos tus datos personales con terceros sin tu consentimiento explícito, a menos que estemos legalmente obligados a hacerlo o sea necesario para brindarte nuestros servicios. En caso de que compartamos tus datos con terceros, nos aseguraremos de que mantengan un nivel adecuado de protección de datos.\n" +
                "\n" +
                "Seguridad de los Datos\n" +
                "a. Nos comprometemos a tomar todas las medidas razonables para proteger tus datos personales contra el acceso no autorizado, el uso indebido o la divulgación. Implementamos medidas de seguridad técnicas y organizativas adecuadas para garantizar la integridad y confidencialidad de tus datos.\n" +
                "\n" +
                "b. Sin embargo, debes tener en cuenta que ninguna medida de seguridad es absoluta y que ninguna transmisión de datos a través de Internet puede garantizarse como 100% segura. Por lo tanto, no podemos garantizar la seguridad completa de tus datos personales y no seremos responsables de cualquier acceso no autorizado o divulgación de información.\n" +
                "\n" +
                "Derechos de Propiedad Intelectual\n" +
                "a. Nuestra aplicación y todos sus contenidos, incluyendo pero no limitado a textos, gráficos, logotipos, imágenes, videos, software y cualquier otro material, están protegidos por derechos de propiedad intelectual y leyes de derechos de autor.\n" +
                "\n" +
                "b. No se te otorga ningún derecho de propiedad intelectual sobre nuestra aplicación o su contenido, excepto por el derecho limitado de uso de la aplicación de acuerdo con estos Términos. Queda estrictamente prohibida la reproducción, distribución, modificación o cualquier otro uso no autorizado de los materiales protegidos por derechos de autor sin nuestro consentimiento previo por escrito.\n" +
                "\n" +
                "Modificaciones y Terminación\n" +
                "a. Nos reservamos el derecho de modificar estos Términos en cualquier momento. Te notificaremos cualquier cambio material en estos Términos a través de la aplicación o por otros medios razonables. Es tu responsabilidad revisar periódicamente estos Términos y estar al tanto de cualquier cambio.\n" +
                "\n" +
                "b. Podemos terminar tu acceso y uso de la aplicación en cualquier momento si consideramos que has violado estos Términos o si creemos que es necesario por razones legales o de seguridad.\n" +
                "\n" +
                "Ley Aplicable y Resolución de Disputas\n" +
                "a. Estos Términos se regirán e interpretarán de acuerdo con las leyes del país en el que estamos establecidos. Cualquier disputa que surja en relación con estos Términos estará sujeta a la jurisdicción exclusiva de los tribunales competentes de ese país.\n" +
                "\n" +
                "Gracias por leer y aceptar nuestros Términos y Condiciones de Uso y Privacidad. Si tienes alguna pregunta o inquietud sobre estos términos, por favor contáctanos a través de los medios proporcionados en nuestra aplicación.", color = Color.White)
        Button(
            onClick = {
                goBack()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text("Volver", color = Color.White)
        }
    }
}