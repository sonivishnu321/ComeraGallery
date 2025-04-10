package com.example.gallaryapp.data.repository

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.gallaryapp.data.model.MediaAlbum
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    GalleryRepository {

    companion object {
        const val ALL_VIDEOS = "All Videos"
        const val ALL_IMAGES = "All Images"
        const val UNKNOWN = "Unknown"
    }

    private var cachedAlbumList: List<MediaAlbum>? = null
    override fun fetchAllMediaImages(): Flow<Result<List<MediaAlbum>>> {
        return flow {
//            Checking cache album
            cachedAlbumList?.let {
                emit(Result.success(it))
                return@flow
            }
//            Fetching albums
            cachedAlbumList = fetchAllMedia()
            cachedAlbumList?.let {
                emit(Result.success(it))
                return@flow
            }
        }.flowOn(Dispatchers.IO)


    }

    override fun fetchAlbumMediaImages(albumName: String): Flow<Result<MediaAlbum>> {
        return flow {
            val imagesList = cachedAlbumList?.filter { it.albumName == albumName } ?: emptyList()
            if (imagesList.isNotEmpty()) {
                emit(Result.success(imagesList.first()))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchAllMedia(): List<MediaAlbum> {
        val albumsMap = LinkedHashMap<String, MutableList<Uri>>()

        val uriList = listOf(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )

        val projection = arrayOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME
        )

        val selection =
            "${MediaStore.MediaColumns.MIME_TYPE} LIKE ? OR ${MediaStore.MediaColumns.MIME_TYPE} LIKE ?"
        val selectionArgs = arrayOf("image/%", "video/%")
        val sortOrder = "${MediaStore.MediaColumns.DATE_ADDED} DESC"

        albumsMap[ALL_IMAGES] = mutableListOf()
        albumsMap[ALL_VIDEOS] = mutableListOf()
        uriList.forEach { mediaUri ->
            context.contentResolver.query(
                mediaUri,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
                val bucketColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val album = cursor.getString(bucketColumn) ?: UNKNOWN
                    val contentUri = ContentUris.withAppendedId(mediaUri, id)
                    // adding media to specific album
                    if (!albumsMap.containsKey(album)) {
                        albumsMap[album] = mutableListOf()
                    }
                    // adding media to all images or all videos album
                    if (isImage(context, contentUri)) {
                        albumsMap[ALL_IMAGES]?.add(contentUri)
                    } else {
                        albumsMap[ALL_VIDEOS]?.add(contentUri)
                    }

                    albumsMap[album]?.add(contentUri)
                }
            }
        }

        return albumsMap.map { (albumName, uris) ->
            MediaAlbum(
                albumName = albumName,
                mediaUris = uris,
                count = uris.size,
                coverUri = uris.first()
            )
        }
    }

    private fun getMimeType(context: Context, uri: Uri): String? {
        return context.contentResolver.getType(uri)
    }

    private fun isImage(context: Context, uri: Uri): Boolean {
        val mimeType = getMimeType(context, uri)
        return mimeType?.startsWith("image") == true
    }
}