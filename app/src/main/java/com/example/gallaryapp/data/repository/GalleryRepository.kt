package com.example.gallaryapp.data.repository

import com.example.gallaryapp.data.model.MediaAlbum
import kotlinx.coroutines.flow.Flow


interface GalleryRepository {
    fun fetchAllMediaImages() : Flow<Result<List<MediaAlbum>>>
    fun fetchAlbumMediaImages(albumName: String) : Flow<Result<MediaAlbum>>
}