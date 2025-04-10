package com.example.gallaryapp.domain.usecases

import com.example.gallaryapp.data.model.MediaAlbum
import com.example.gallaryapp.data.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageListUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository
) {
    operator fun invoke(albumName: String) : Flow<Result<MediaAlbum>> {
        return galleryRepository.fetchAlbumMediaImages(albumName = albumName)
    }
}