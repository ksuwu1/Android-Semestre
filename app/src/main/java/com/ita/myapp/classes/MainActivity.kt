package com.ita.myapp.classes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ita.myapp.classes.ui.theme.Myapp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { //Lo que se imprime en pantalla
            Column(
                modifier = Modifier.fillMaxSize(), //De esa columna ocupa todo el espacio
                verticalArrangement = Arrangement.Center, //Alinear verticalmente
                horizontalAlignment = Alignment.CenterHorizontally //Centrar horizontalmente
            ) {
                CustomText()
                Picture()
            }
        }
    }
}

//Cada Composable es un elemento visible
@Composable //Fragmento o componente de diseño
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //Modifier te permite acceder a ciertas propiedades de los elementos
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
        modifier = Modifier
            .padding(24.dp) //dp es la medida en android
    ) {
        Text(text = "Hello World")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() }) //Evento de click
    ) {
        Text(text = "Hello World")
    }
}

fun clickAction() {
    println("Column Clicked")
}

@Preview(showBackground = true)
@Composable
fun ModifierExample3() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.Cyan)
            .border(width = 2.dp, color = Color.Green)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
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
        androidx.compose.foundation.Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.androidlogo),
            contentDescription = "Logo Android",
            contentScale = ContentScale.Crop
        )
    }
}
