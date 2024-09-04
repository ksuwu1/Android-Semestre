package com.ita.myapp.classes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ita.myapp.classes.ui.theme.Myapp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //layouts
            Column{ //The main component
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
//                }
//            }
        }
    }
}

@Composable //Fragmento o componente de diseño
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