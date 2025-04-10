package com.example.comeragallary.ui.gallery.image

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage

@Composable
fun FullScreenImage(uri: Uri?, cancel: () -> Unit) {
    Box {
        AsyncImage(
            model = uri,
            contentDescription = "",
            modifier = Modifier.fillMaxSize().
            background(color = Color.Gray)
                .clickable {  }
        )
        IconButton(onClick = cancel,
            modifier = Modifier
                .padding(end = 30.dp, top = 30.dp)
                .align(alignment = Alignment.TopEnd)
                .size(20.dp)
                .zIndex(5f)
                .clickable { cancel() }
                .background(color = Color.White, shape = RoundedCornerShape(5.dp))) {
            Icon(Icons.Default.Close, contentDescription = "Back")
        }
    }
}
