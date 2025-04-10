package com.example.gallaryapp.ui.gallery.imagelist

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.gallaryapp.data.model.MediaAlbum
import com.example.gallaryapp.domain.usecases.ImageListUseCase
import com.example.gallaryapp.ui.gallery.BaseGalleryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageListUseCase: ImageListUseCase
) : BaseGalleryViewModel() {
    private val _uiState: MutableStateFlow<ImageListUiState> =
        MutableStateFlow(ImageListUiState.LOADING)
    var uiState = _uiState.asStateFlow()

    private var album: MediaAlbum? = null

    fun fetchAlbumImages(albumName: String) {
        viewModelScope.launch {
            _uiState.update {
                ImageListUiState.LOADING
            }
            withContext(Dispatchers.IO) {
                imageListUseCase(albumName = albumName).collect {
                    if (it.isSuccess) {
                        album = it.getOrNull()
                        album?.let {
                            println(album)
                            _uiState.update { _ ->
                                ImageListUiState.SUCCESS(it, viewType)
                            }
                        }
                    }
                }
            }
        }
    }


    fun showFullImage(imageUri: Uri) {
        album?.let {
            _uiState.update { _ ->
                ImageListUiState.SUCCESS(it, viewType, imageUri = imageUri)
            }
        }
    }

    fun hideFullImage() {
        album?.let {
            _uiState.update { _ ->
                ImageListUiState.SUCCESS(it, viewType, imageUri = null)
            }
        }
    }

    override fun toggleViewType() {
        super.toggleViewType()
        album?.let {
            _uiState.update { _ ->
                ImageListUiState.SUCCESS(it, viewType)
            }
        }
    }
}