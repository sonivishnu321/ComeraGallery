package com.example.comeragallary.ui.gallery.album

import androidx.lifecycle.viewModelScope
import com.example.comeragallary.data.model.MediaAlbum
import com.example.comeragallary.domain.usecases.AlbumListUseCase
import com.example.comeragallary.ui.gallery.BaseGalleryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val albumUseCase: AlbumListUseCase
) : BaseGalleryViewModel() {
    private val _uiState: MutableStateFlow<AlbumUiState> = MutableStateFlow(AlbumUiState.EMPTY)
    var uiState = _uiState.asStateFlow()
    private var albumList: List<MediaAlbum> = emptyList()
    fun fetchMedia() {
        viewModelScope.launch {
            _uiState.update {
                AlbumUiState.LOADING
            }
            albumUseCase().collect {
                if (it.isSuccess) {
                    albumList = it.getOrDefault(emptyList())
                    println(albumList)
                    _uiState.update {
                        AlbumUiState.SUCCESS(albumList, viewType)
                    }
                } else {
                    _uiState.update {
                        AlbumUiState.EMPTY
                    }
                }
            }

        }
    }

    override fun toggleViewType() {
        super.toggleViewType()
        _uiState.update {
            AlbumUiState.SUCCESS(albumList, viewType)
        }
    }
}