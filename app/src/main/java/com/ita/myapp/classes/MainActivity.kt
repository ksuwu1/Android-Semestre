package com.ita.myapp.classes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMultiScreenApp()
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
        Greeting("Cass")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier.padding(24.dp)
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
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            // Imagen a la izquierda
            Image(
                painter = painterResource(id = R.drawable.androidlogo),
                contentDescription = "Android logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.Crop
            )

            // Texto a la derecha de la imagen
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                // Título
                Text(
                    text = "Titul0",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Hola",
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.androidlogo),
            contentDescription = "Android Logo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        // Ajustamos el Row dentro del Box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 150.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // Ícono
            Icon(
                painter = painterResource(R.drawable.ic_account_box),
                contentDescription = "Icon"
            )

            // Texto
            Text(text = "Text")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample2() {
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .padding(5.dp)
            .size(250.dp)
    ) {
        Text(text = "TopStart", Modifier.align(Alignment.TopStart))
        Text(text = "TopEnd", Modifier.align(Alignment.TopEnd))
        Text(text = "CenterStart", Modifier.align(Alignment.CenterStart))
        Text(text = "Center", Modifier.align(Alignment.Center))
        Text(text = "CenterEnd", Modifier.align(Alignment.CenterEnd))
        Text(text = "BottomStart", Modifier.align(Alignment.BottomStart))
        Text(text = "BottomEnd", Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun ComposeMultiScreenApp() {
    val navController = rememberNavController()
    Surface(color = Color.White) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController= navController, startDestination= "menu"){
        composable(route:"menu"){MenuScreen(NavController)}
        composable(route:"home"){HomeScreen(NavController)}

    }
    {
        // Aquí se pueden agregar destinos de navegación
    }
}
