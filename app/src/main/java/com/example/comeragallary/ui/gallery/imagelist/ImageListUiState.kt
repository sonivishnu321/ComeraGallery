package com.example.comeragallary.ui.gallery.imagelist

import android.net.Uri
import com.example.comeragallary.data.model.MediaAlbum
import com.example.comeragallary.ui.gallery.album.ViewType

sealed class ImageListUiState {
    data object LOADING : ImageListUiState()
    class SUCCESS(val album: MediaAlbum, val viewType: ViewType, val imageUri: Uri?= null) : ImageListUiState()
}
