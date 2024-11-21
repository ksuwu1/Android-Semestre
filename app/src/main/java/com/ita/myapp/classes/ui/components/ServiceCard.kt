package com.ita.myapp.classes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
// Asegúrate de usar el paquete correcto para el recurso

@Composable
fun ServiceCard(
    id: Int,
    name: String,
    username: String,
    imageURL: String?,
    onButtonClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Ajuste para una mejor separación
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Margen interno para el contenido
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del servicio
            AsyncImage(
                model = imageURL,
                contentDescription = "Service Logo",
                modifier = Modifier
                    .size(80.dp) // Tamaño compacto para la imagen
                    .padding(end = 8.dp),
                error = painterResource(R.drawable.androidlogo), // Recurso de imagen de error
                contentScale = ContentScale.Crop
            )
            // Column para los textos
            Column(
                modifier = Modifier.weight(1f) // Usar peso para distribuir el espacio
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = username,
                    fontSize = 15.sp
                )
            }
            // Botón de opciones
            IconButton(
                onClick = { onButtonClick() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Service Details",
                    tint = Color.White
                )
            }
        }
    }
}
