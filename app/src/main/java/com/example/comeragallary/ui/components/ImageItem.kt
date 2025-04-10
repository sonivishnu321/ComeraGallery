package com.example.comeragallary.ui.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun ListImageItem(imageUri: Uri, modifier: Modifier) {
    AsyncImage(
        model = imageUri,
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = modifier.fillMaxWidth().aspectRatio(4/3f)
            .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    )
    Spacer(modifier = Modifier.size(5.dp))
}
@Composable
fun GridImageItem(imageUri: Uri, modifier: Modifier) {
    AsyncImage(
        model = imageUri,
        contentDescription = "",
        modifier = modifier.size(200.dp)
            .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    )
}