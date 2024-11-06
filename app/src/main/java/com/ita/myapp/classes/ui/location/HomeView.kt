package com.ita.myapp.classes.ui.location

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedButton
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeView(navController: NavController, searchVM: SearchViewModel) {
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de texto para ingresar la dirección
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Ingrese una dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para buscar las coordenadas de la dirección
        Button(
            onClick = {
                // Aquí invocas la función para obtener las coordenadas de la dirección
                if (address.isNotEmpty()) {
                    searchVM.getLocation(address)
                    Log.d("HomeView", "Obteniendo ubicación para: $address")
                } else {
                    Toast.makeText(navController.context, "Por favor ingrese una dirección", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Buscar")
        }

        // Verificar si la dirección fue encontrada y si las coordenadas están disponibles
        if (searchVM.lat != 0.0 && searchVM.long != 0.0) {
            Text("Latitud: ${searchVM.lat}, Longitud: ${searchVM.long}", fontSize = 20.sp)
            Text("Dirección: ${searchVM.address}", fontSize = 20.sp)
        }

        // Botón para enviar la información al mapa
        OutlinedButton(onClick = {
            if (searchVM.lat != 0.0 && searchVM.long != 0.0) {
                navController.navigate("MapsSearchView/${searchVM.lat}/${searchVM.long}/${searchVM.address}")
            } else {
                Toast.makeText(navController.context, "No se encontraron coordenadas válidas", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Ver en mapa")
        }
    }
}
