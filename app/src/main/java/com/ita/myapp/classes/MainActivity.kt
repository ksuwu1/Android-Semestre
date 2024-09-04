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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ita.myapp.classes.ui.theme.Myapp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { //Lo que se imprime en pantalla

            Column(
                modifier= Modifier.fillMaxSize(), //De esa columna ocupa todo el espacio
                verticalArrangement = Arrangement.Center, //Donde lo quieres alinear de forma vertical
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally //Centrarlo horizontalmente
            ){
                Text(text = "Simple Text")
                ModifierExample()
                ModifierExample2()
                ModifierExample3()
            }


            //layouts
            /*Column{ //The main component // Solo se pone una vez
                Text(text="First Row")
                Text(text="Second Row")
                Text(text="Third Row")
                Row{
                    Text(text="TEXT 1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")

                }
                Box{ //Encima los elementos
                    Text(text = "Label 1")
                    Text(text = "Label 2")
                }
                Greeting(name = "World")
            }
//            Myapp2Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//
    }
//            }*/
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

@Preview(showBackground = true) //Si este se modifica, se muestra en tiempo real
@Composable
fun GreetingPreview() {
    Myapp2Theme {
        Greeting("Tony")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample(){
    Column(
        modifier=Modifier
            .padding(24.dp) //dp es la medida en android


    ){
        Text(text="Hello World")

    }
}


@Preview(showBackground = true)
@Composable
fun ModifierExample2(){
    Column(
        modifier= Modifier
            .padding(24.dp) //dp es la medida en android
            .fillMaxWidth()
            .clickable(onClick = { clickAction() }) //Evento de click
    ){
        Text(text="Hello World")

    }
}

fun clickAction(){
    println("Column Clicked")
}

@Preview(showBackground = true)
@Composable
fun ModifierExample3(){
    Column(
        modifier = Modifier
            .fillMaxHeight() //Ocupa todo el ancho
            .padding(16.dp)
            .background(Color.Cyan) // Color de la columna
            .border(width = 2.dp, color = Color.Green)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally, //Propias de Android Compose
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
    }


}