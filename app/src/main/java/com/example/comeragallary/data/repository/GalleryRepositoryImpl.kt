package com.example.comeragallary.data.repository

import com.example.comeragallary.data.model.MediaAlbum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepositoryImpl @Inject constructor() :
    GalleryRepository {

    private var cachedAlbumList: List<MediaAlbum> ?= null
    override fun fetchAllMediaImages(): Flow<Result<List<MediaAlbum>>> {
        return flow {
            cachedAlbumList = fetchAllMedia()
            cachedAlbumList?.let {
                emit(Result.success(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchAlbumMediaImages(albumName: String): Flow<Result<MediaAlbum>> {
        return flow {
            val imagesList = cachedAlbumList?.filter { it.albumName == albumName } ?: emptyList()
            if(imagesList.isNotEmpty()) {
                emit(Result.success(imagesList.first()))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchAllMedia(): List<MediaAlbum> {
       return listOf(MediaAlbum(albumName = "camera"), MediaAlbum(albumName = "screenshot"))
    }
}