package com.example.gallaryapp.ui.gallery.album

import com.example.gallaryapp.data.model.MediaAlbum

sealed class AlbumUiState {
    data object LOADING : AlbumUiState()
    data object EMPTY: AlbumUiState()
    class SUCCESS(val albumList: List<MediaAlbum>, val viewType : ViewType) : AlbumUiState()
}

enum class ViewType {
    GRID_VIEW,
    LIST_VIEW
}