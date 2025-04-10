package com.example.comeragallary.domain.usecases

import com.example.comeragallary.data.model.MediaAlbum
import com.example.comeragallary.data.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumListUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository
) {
    operator fun invoke() : Flow<Result<List<MediaAlbum>>> {
        return galleryRepository.fetchAllMediaImages()
    }
}