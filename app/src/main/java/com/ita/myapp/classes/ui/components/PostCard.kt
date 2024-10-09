package com.ita.myapp.classes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ita.myapp.classes.R


//@Preview(showBackground = true)
@Composable
fun PostCard(id: Int, title : String, text:String, image: Painter){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ){
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            painter = image,
            contentDescription = "Android logo",
            contentScale = ContentScale.Crop
        )

        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(10.dp)
        )

    }
}