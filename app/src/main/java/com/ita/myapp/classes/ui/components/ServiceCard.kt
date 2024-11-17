package com.ita.myapp.classes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ServiceCardPreview() {
    // Usar una imagen de recurso predeterminada para la vista previa
    val dummyImage = painterResource(id = android.R.drawable.ic_menu_info_details) // Imagen predeterminada del sistema

    ServiceCard(
        id = 1,
        title = "Servicio de ejemplo",
        subtitle = "Subtítulo del servicio",
        text = "Este es un texto descriptivo adicional sobre el servicio ofrecido. Puede incluir más detalles y características.",
        image = dummyImage
    )
}

@Composable
fun ServiceCard(
    id: Int,
    title: String,
    subtitle: String,
    text: String,
    image: Painter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // Imagen a la izquierda
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(end = 10.dp),
                painter = image,
                contentDescription = "Imagen",
                contentScale = ContentScale.Crop
            )

            // Column con título, subtítulo y botón
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Botón a la derecha de los títulos
            Button(
                onClick = { /* Acción del botón */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Botón", fontSize = 14.sp)
            }
        }

        // Texto adicional debajo
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}