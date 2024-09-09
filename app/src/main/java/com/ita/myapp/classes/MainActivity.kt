package com.ita.myapp.classes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ita.myapp.classes.ui.theme.Myapp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Lo que se imprime en pantalla
            Column(
                modifier = Modifier
                    .fillMaxSize() // La columna ocupa todo el espacio
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center, // Alinear verticalmente
                horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
            ) {
                CustomText()
                Picture()
                Content1()
                Content2()
            }
        }
    }
}

// Cada Composable es un elemento visible
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Myapp2Theme {
        Greeting("Tony")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier.padding(24.dp) // dp es la medida en Android
    ) {
        Text(text = "Hello World")
    }
}

@Composable
fun CustomText() {
    Column {
        Text(
            text = stringResource(R.string.Hello_World_Text),
            color = colorResource(R.color.purple_700),
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Color.Cyan, Color.Blue)
        Text(
            text = stringResource(R.string.Hello_World_Text),
            style = TextStyle(brush = Brush.linearGradient(gradientColors))
        )
    }
}

@Composable
fun Picture() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.androidlogo),
            contentDescription = "Logo Android",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Content1() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "This is a title",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.androidlogo),
                contentDescription = "Android Logo",
                contentScale = ContentScale.Crop
            )

            Text(text = "Test")

            Text(
                text = stringResource(R.string.uwu),
                textAlign = TextAlign.Justify,
                lineHeight = 10.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Content2() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row( // Usamos Row para alinear horizontalmente
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically // Alinear elementos verticalmente
        ) {
            Image(
                modifier = Modifier

                    .padding(end = 10.dp), // Espacio entre la imagen y los textos
                painter = painterResource(id = R.drawable.androidlogo),
                contentDescription = "Android Logo",
                contentScale = ContentScale.Crop
            )

            // Los textos alineados a la derecha de la imagen
            Column(
                modifier = Modifier
                    .fillMaxWidth(), // Ocupar el ancho restante
                verticalArrangement = Arrangement.Center // Centrar verticalmente los textos
            ) {
                Text(
                    text = "This is a title",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(text = "Test")

                Text(
                    text = stringResource(R.string.uwu),
                    textAlign = TextAlign.Justify,
                    lineHeight = 10.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
            }
        }
    }
}
