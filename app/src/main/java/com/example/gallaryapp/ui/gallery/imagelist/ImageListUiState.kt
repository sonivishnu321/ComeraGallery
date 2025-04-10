package com.example.gallaryapp.ui.gallery.imagelist

import android.net.Uri
import com.example.gallaryapp.data.model.MediaAlbum
import com.example.gallaryapp.ui.gallery.album.ViewType

sealed class ImageListUiState {
    data object LOADING : ImageListUiState()
    class SUCCESS(val album: MediaAlbum, val viewType: ViewType, val imageUri: Uri?= null) : ImageListUiState()
}
