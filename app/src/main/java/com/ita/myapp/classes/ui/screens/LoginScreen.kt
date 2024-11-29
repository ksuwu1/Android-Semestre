package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import com.ita.myapp.classes.data.model.network.RetrofitClient
import com.ita.myapp.classes.data.model.network.ApiService
import com.ita.myapp.classes.data.model.network.LoginRequest
import retrofit2.Response


@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var loginTrigger by remember { mutableStateOf(false) } // Trigger for login

    // Function to handle login (as a suspend function)
    suspend fun login() {
        isLoading = true
        val loginRequest = LoginRequest(username, password)
        val response: Response<Unit> = RetrofitClient.api.login(loginRequest)
        isLoading = false
        if (response.isSuccessful) {
            // Navigate to HomeScreen if login is successful
            navController.navigate("home") {
                // Optionally, clear the back stack if needed
                popUpTo("login") { inclusive = true }
            }
        } else {
            errorMessage = "Invalid username or password"
        }
    }

    // Observe the login trigger and call the suspend function when triggered
    LaunchedEffect(loginTrigger) {
        if (loginTrigger) {
            login()
            loginTrigger = false // Reset the trigger after login attempt
        }
    }

    // Display UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                // Trigger the login process
                loginTrigger = true
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Login")
        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
        }
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ShowLoginForm() {
    LoginForm(navController = rememberNavController())
}

@Composable
fun LoginForm(navController: NavController){

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ),
        modifier = Modifier
            .padding(40.dp,0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ){
            //Cargar recursos desde una URL
            /*AsyncImage(
                model  ="https://logoscarcas.net/wp-content/uploads/2020/12/GitHub-",
                contentDescription ="Github logo",
                contentScale=ContentScale.Fit
            )*/
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = user,
                onValueChange = { user = it },
                label = { Text("User") }
            )


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    navController.navigate("home")
                }
            ) {
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Text("CREATE AN ACCOUNT")
            }
        }
    }
}