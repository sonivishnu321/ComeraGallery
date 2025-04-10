package com.example.comeragallary.data.repository

import com.example.comeragallary.data.model.MediaAlbum
import kotlinx.coroutines.flow.Flow


interface GalleryRepository {
    fun fetchAllMediaImages() : Flow<Result<List<MediaAlbum>>>
    fun fetchAlbumMediaImages(albumName: String) : Flow<Result<MediaAlbum>>
}