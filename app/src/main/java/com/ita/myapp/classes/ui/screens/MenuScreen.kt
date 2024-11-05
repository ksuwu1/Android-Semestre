package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController){
    Column{
        Text(text="This is the HomeScreen")
        Button(onClick = { navController.navigate("menu")}) {
            Text(text= "Menu")
        }

        Column{
            Text(text="This is the APisScreen")
            Button(onClick = { navController.navigate("apis")}) {
                Text(text= "APis")
            }

            Text(text="This is the ComponentsScreen")
            Button(onClick = { navController.navigate("components")}) {
                Text(text= "Components")
            }
        }
    }
}