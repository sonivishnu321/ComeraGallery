package com.example.comeragallary.data.model

import android.net.Uri

data class MediaAlbum(
    val albumName: String,
    val mediaUris: List<Uri>,
    val count: Int,
    val coverUri: Uri
)